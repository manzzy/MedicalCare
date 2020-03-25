package com.example.medicalcare.Fragment;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalcare.Helper.DatabaseHelper;
import com.example.medicalcare.R;
import com.example.medicalcare.Adapter.TipAdapter;
import com.example.medicalcare.Model.TipModel;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipFragment extends Fragment {
    RecyclerView mview;
    TipAdapter madapter;
    DatabaseHelper db;

    public TipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tip_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DatabaseHelper(view.getContext());
        mview = view.findViewById(R.id.tip_recyclerview);
        madapter = new TipAdapter(view.getContext(),add());
        mview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mview.setItemAnimator(new DefaultItemAnimator());
        mview.setAdapter(madapter);

    }


    public ArrayList<TipModel> add(){
        ArrayList<TipModel> str = new ArrayList<>();
        Cursor res= db.getDataForRecycler();
        if(res.getCount() ==0){
            TipModel tipModel= new TipModel("Empty", "Empty List!!!");
            str.add(tipModel);
            return str;
        }
        TipModel tipModel;
        while(res.moveToNext()){
            String diseaseName,shortDiscription;

            diseaseName = res.getString(0);
            shortDiscription = res.getString(1);


            tipModel = new TipModel(diseaseName,shortDiscription);
            str.add(tipModel);
        }


        return str;
    }

}
