package com.example.medicalcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcare.Model.Alarm;
import com.example.medicalcare.R;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    Context context;
    private ArrayList<Alarm> mdata;

    public AlarmAdapter(Context context, ArrayList<Alarm> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alarm alarm= mdata.get(position);
        holder.alarmTime.setText(alarm.getTime());
        holder.alarmMedicine.setText(alarm.getMedicine());
        holder.aSwitch.setChecked(alarm.getOn());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView alarmTime, alarmMedicine;
        CardView mcard;
        Switch aSwitch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmTime = itemView.findViewById(R.id.alarm_time);
            alarmMedicine = itemView.findViewById(R.id.alarm_medicne);
            aSwitch = itemView.findViewById(R.id.switch1);

        }
    }
}
