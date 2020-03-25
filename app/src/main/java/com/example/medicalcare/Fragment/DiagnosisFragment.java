package com.example.medicalcare.Fragment;


import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalcare.Helper.DatabaseHelper;
import com.example.medicalcare.Model.TipModel;
import com.example.medicalcare.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiagnosisFragment extends Fragment {
    CheckBox headache, nausea, cough,fever,abdominalPain,diarrhea,breath,thrist,urination,hunger;
    TextView res;
    Button btn;
    private ArrayList<String> mResult;
    DatabaseHelper db;
    Dialog mDialog;
    String title="", description="";


    public DiagnosisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_diagnosis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        headache = view.findViewById(R.id.headache);
        nausea = view.findViewById(R.id.nausea);
        cough = view.findViewById(R.id.cough);
        fever = view.findViewById(R.id.fever);
        abdominalPain = view.findViewById(R.id.abdominal_pain);
        diarrhea = view.findViewById(R.id.diarrhea);
        breath = view.findViewById(R.id.breath);
        thrist = view.findViewById(R.id.thrist);
        urination = view.findViewById(R.id.urination);
        hunger = view.findViewById(R.id.hunger);
        btn = view.findViewById(R.id.submit);
        res = view.findViewById(R.id.result);


        mDialog = new Dialog(view.getContext());
        db = new DatabaseHelper(view.getContext());

        mResult = new ArrayList<>();
        res.setEnabled(false);

            // set click listener to the submit button to modify the Content

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(headache.isChecked()){
                    mResult.add("Headache");
                }
                if(nausea.isChecked()){
                    mResult.add("Nausea");
                }
                if(cough.isChecked()){
                    mResult.add("Cough");
                }
                if(fever.isChecked()){
                    mResult.add("Fever");
                }
                if(abdominalPain.isChecked()){
                    mResult.add("AbdominalPain");
                }
                if(diarrhea.isChecked()){
                    mResult.add("Diarrhea");
                }
                if(breath.isChecked()){
                    mResult.add("Breath");
                }
                if(thrist.isChecked()){
                    mResult.add("Thirst");
                }
                if(urination.isChecked()){
                    mResult.add("Urination");
                }
                if(hunger.isChecked()){
                    mResult.add("Hunger");
                }
                getData(mResult);

            }
        });
        }



    public void getData(ArrayList<String> data){
        StringBuilder symptom= new StringBuilder();

        for(String s : data){
            symptom.append(s);
            symptom.append(",");
        }
        mResult.clear();
        Toast.makeText(getContext(), symptom, Toast.LENGTH_LONG).show();
        String symp = symptom.toString();
        Cursor res= db.getDataForDiagnosed(symp);
        if(res.getCount() ==0){
            title= "Sorry";
            description = "Couldn't find Matching Disease to Your Symptoms";
        }

        while(res.moveToNext()){
            title = res.getString(0);
            description = res.getString(1);

        }


        showpopup();

    }

    public void showpopup( ){
        TextView head, description;
        Button mFinishBtn;
        mDialog.setContentView(R.layout.dialog_item);
        head = mDialog.findViewById(R.id.popup_header);
        description = mDialog.findViewById(R.id.popup_description);
        head.setText(title);
        description.setText(this.description);
        mFinishBtn= mDialog.findViewById(R.id.popup_btn);
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();

            }
        });
        mDialog.show();
    }
}
