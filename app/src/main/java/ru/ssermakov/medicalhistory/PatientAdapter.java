package ru.ssermakov.medicalhistory;

import android.content.Context;
import android.database.Cursor;
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
    private Cursor cursor;

    public PatientAdapter(Context context, List<Patient> patientList, Cursor cursor) {
        this.context = context;
        this.patientList = patientList;
        this.cursor = cursor;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.f_recycler_layout, null);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String name = cursor.getString(cursor.getColumnIndex(PatientsTable.COLUMN_NAME));
        String illness_id = cursor.getString(cursor.getColumnIndex(PatientsTable.COLUMN_ILLNESS_ID));
        String current_state = cursor.getString(cursor.getColumnIndex(PatientsTable.COLUMN_CURRENT_STATE));
        String image_url = cursor.getString(cursor.getColumnIndex(PatientsTable.COLUMN_IMAGE_URL));
        long id = cursor.getLong(cursor.getColumnIndex(PatientsTable.COLUMN_ID));

        holder.textViewName.setText(name);
        holder.textViewStatus.setText(current_state);
        holder.itemView.setTag(id);

        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.kate));

// Эта часть использовалась при работе с удаленной базой данных
/*        Patient patient = patientList.get(position);

        holder.textViewName.setText(patient.getName());
        holder.textViewStatus.setText(patient.getStatus());

        holder.imageView.setImageDrawable(context.getResources().getDrawable(patient.getImg()));*/
    }

    @Override
    public int getItemCount() {
//        return patientList.size(); Для удаленной БД
        return cursor.getCount();
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

    public void swapCursor (Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
