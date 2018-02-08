package ru.ssermakov.medicalhistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by btb_wild on 07.02.2018.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private Context context;
    private List<Patient> patientList;

    public PatientAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.f_recycler_layout, null);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        Patient patient = patientList.get(position);

        holder.textViewName.setText(patient.getName());
        holder.textViewStatus.setText(patient.getStatus());

        holder.imageView.setImageDrawable(context.getResources().getDrawable(patient.getImg()));
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    class PatientViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewStatus;

        public PatientViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
        }
    }
}
