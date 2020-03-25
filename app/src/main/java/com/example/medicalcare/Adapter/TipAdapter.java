package com.example.medicalcare.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcare.Helper.DatabaseHelper;
import com.example.medicalcare.R;
import com.example.medicalcare.Model.TipModel;

import java.util.ArrayList;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private ArrayList<TipModel> mdata;
    Context context;

    public TipAdapter(Context context,ArrayList<TipModel> mdata) {
        this.mdata = mdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TipModel data = mdata.get(position);
        holder.title.setText(data.getTitle());
        holder.description.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,description;
        CardView mcard;
        Dialog mDialog;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_discription);
            mcard = itemView.findViewById(R.id.carier);
            mDialog = new Dialog(itemView.getContext());
            mcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showpopup();

                }
            });

        }

        private void showpopup() {
            DatabaseHelper db = new DatabaseHelper(itemView.getContext());
            Cursor res = db.getDataForPopUp(title.getText().toString());
            String des="";
            String diseaseName="";

            if(res.getCount() ==0){
                return ;
            }
            while(res.moveToNext()){
                 diseaseName= res.getString(0);
                des = res.getString(1);
            }
            TextView head, description;
            Button mFinishBtn;
            mDialog.setContentView(R.layout.dialog_item);
            head = mDialog.findViewById(R.id.popup_header);
            description = mDialog.findViewById(R.id.popup_description);
            head.setText(diseaseName);
            description.setText(des);
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
}
