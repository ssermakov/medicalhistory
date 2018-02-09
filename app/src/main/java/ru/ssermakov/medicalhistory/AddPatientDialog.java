package ru.ssermakov.medicalhistory;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by btb_wild on 08.02.2018.
 */

public class AddPatientDialog extends DialogFragment {
    AddPatientEventListener patientEventListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        patientEventListener = (AddPatientEventListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_patient_dialog, null);
        final EditText editText = view.findViewById(R.id.etAddPatient);

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Add Patient Form")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        patientEventListener.addPatient(editText.getText().toString());
                    }
                })
                .setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setMessage("message");
        return adb.create();
    }
}
