package com.example.medicalcare.Fragment;


import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalcare.Adapter.AlarmAdapter;
import com.example.medicalcare.Dashboard;
import com.example.medicalcare.Helper.DatabaseHelper;
import com.example.medicalcare.Model.Alarm;
import com.example.medicalcare.Model.TipModel;
import com.example.medicalcare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicneAlarmFragment extends Fragment {
    RecyclerView mrec;
    AlarmAdapter madpater;
    ArrayList<Alarm> mdata;
    Dialog mDialog;
    FloatingActionButton plus;
    DatabaseHelper db;
    String name;

    public String getName() {
        return name;
    }

    public MedicneAlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_medicne_alarm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mrec= view.findViewById(R.id.recyclerView);
        plus = view.findViewById(R.id.addbtn);
        mDialog = new Dialog(view.getContext());
        name = "";
        db = new DatabaseHelper(view.getContext());


        mdata = new ArrayList<>();
        //refresh("10 : 40", "Panadol", true);
        //refresh("01 : 20", "Mizzle", false);
        mrec.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mrec.setItemAnimator(new DefaultItemAnimator());
        madpater = new AlarmAdapter(view.getContext(),mdata);

        if(mdata != null){
            mrec.setAdapter(madpater);
        }
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopup();
            }
        });

    }

    public void refresh(String t, String m, Boolean ischecked){
        mdata.add(new Alarm(t,m,ischecked));
    }
    public void showpopup( ){
        final EditText medicineName;
        final Button mFinishBtn;
        mDialog.setContentView(R.layout.alarm_input_dialog);
        medicineName = mDialog.findViewById(R.id.alarm_name);
        mFinishBtn= mDialog.findViewById(R.id.set_alarmbtn);
        mFinishBtn.setEnabled(false);
        if(medicineName != null){
            mFinishBtn.setEnabled(true);
        }
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = medicineName.getText().toString();
                time();
                mDialog.dismiss();

            }
        });
        mDialog.show();
    }
    public void time(){
        DialogFragment df= new TimePickerFragment();
        df.show(getActivity().getSupportFragmentManager(),"Time Picker");

        addData();

    }
    public void addData(){
        Dashboard dash= new Dashboard();
        while(true){
            if(dash.getFlag() ==0 ) {


                Cursor res = db.getAlarm();
                if (res.getCount() == 0) {

                }

                while (res.moveToNext()) {
                    String name;
                    int hour, minute;

                    name = res.getString(0);
                    hour = res.getInt(1);
                    minute = res.getInt(2);


                    mdata.add(new Alarm(name, hour + " : " + minute, true));
                }
                return;
            }

        }




    }

}
