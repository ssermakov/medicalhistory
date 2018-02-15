package ru.ssermakov.medicalhistory;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements AddPatientEventListener {
//implements Callback<Result>,


    private RecyclerView recyclerView;
    private ArrayList<Patient> patientList;
    private PatientAdapter patientAdapter;

    private DbService service;
    private Retrofit retrofit;
    private boolean loading = false;

    private DialogFragment addPatientDialog;
    private DBHelper helper;
    private Cursor cursor;

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientList = new ArrayList<>();

        recyclerView = findViewById(R.id.f_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new DBHelper(this);

        cursor = getAllPatientsFromLocalDB();

//        patientList = createPatientList(); для удаленной БД
        patientAdapter = new PatientAdapter(MainActivity.this, patientList, cursor);
        recyclerView.setAdapter(patientAdapter);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        addPatientDialog = new AddPatientDialog();













//Метод загрузки данных с сервера via Retrofit и занесения данных в ресайклер. Закомментирован до разработки серверной части


/*        App.getDbService().getChildren().enqueue(new Callback<Result>() {
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
        });*/


    }

    private void removeItem(long tag) {
        helper.getWritableDatabase().delete(
                PatientsTable.TABLE_PATIENTS,
                PatientsTable.COLUMN_ID + "=" + tag,
                null
        );
        patientAdapter.swapCursor(getAllPatientsFromLocalDB());
    }

    private Cursor getAllPatientsFromLocalDB() {
        return helper.getReadableDatabase().query(
                PatientsTable.TABLE_PATIENTS,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

/*    private ArrayList<Patient> createPatientList() {
        while (cursor.moveToNext()) {
            Patient patient = new Patient(
                    cursor.getString(cursor.getColumnIndex(PatientsTable.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(PatientsTable.COLUMN_CURRENT_STATE)),
                    R.drawable.kate
            );
            patientList.add(patient);
        }
        return patientList;
    }*/

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
// patientList.add(new Patient(patientName, "1", R.drawable.kate)); для удаленной БД

        ContentValues cv = new ContentValues();

        cv.put(PatientsTable.COLUMN_NAME, patientName);
        cv.put(PatientsTable.COLUMN_ILLNESS_ID, "0");
        cv.put(PatientsTable.COLUMN_CURRENT_STATE, "1");
        cv.put(PatientsTable.COLUMN_IMAGE_URL, "no url");

        helper.getWritableDatabase().insert(PatientsTable.TABLE_PATIENTS, null,cv);
        patientAdapter.swapCursor(getAllPatientsFromLocalDB());
    }
}
