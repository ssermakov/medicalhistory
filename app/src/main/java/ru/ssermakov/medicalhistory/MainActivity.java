package ru.ssermakov.medicalhistory;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AddPatientEventListener {
//implements Callback<Result>,


    private RecyclerView recyclerView;
    private List<Patient> patientList;
    private PatientAdapter patientAdapter;

    private DbService service;
    private Retrofit retrofit;
    private boolean loading = false;

    private DialogFragment addPatientDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientList = new ArrayList<>();

        recyclerView = findViewById(R.id.f_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        App.getDbService().getChildren().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getStat().equals("ok")) {
                    Children body = (response.body().getChildren());
                    for (Child r : body.getChild()){
                        patientList.add(
                                new Patient(
                                        r.getName(),
                                        r.getCurrentState(),
                                        R.drawable.kate));
                    }
                }
                patientAdapter = new PatientAdapter(MainActivity.this, patientList);
                recyclerView.setAdapter(patientAdapter);
                loading = false;
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                showToast("Something wrong");
            }
        });

        addPatientDialog = new AddPatientDialog();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void showToast (String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.fm_add_patient) {
            showAddPatientDialog();
        }
        if (item.getItemId() == R.id.fm_search_patient) {
            showToast("Search");
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddPatientDialog() {
        addPatientDialog.show(getFragmentManager(), "addPatientDialog");
    }

    @Override
    public void addPatient(String patientName) {
        patientList.add(new Patient(patientName, "", R.drawable.kate));
        patientAdapter.notifyDataSetChanged();
    }
}
