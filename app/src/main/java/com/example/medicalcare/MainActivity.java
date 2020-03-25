package com.example.medicalcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medicalcare.Adapter.SlideAdapter;
import com.example.medicalcare.Helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private ViewPager mview;
    private LinearLayout mlayout;
    private SlideAdapter madapter;
    TextView[] mDots;
    private int current;
    DatabaseHelper db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mview = findViewById(R.id.viewpager);
        mlayout = findViewById(R.id.linearlayout);
        db = new DatabaseHelper(this);
        sp = getSharedPreferences("checker", MODE_PRIVATE);

        if(sp.getBoolean("isChecked",false)){
            Intent intent= new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();

        }

        madapter = new SlideAdapter(this);
        mview.setAdapter(madapter);
        addDotsIndicator(0);

        mview.addOnPageChangeListener(viewListener);


    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mlayout.removeAllViews();

        for(int i=0 ; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mlayout.addView(mDots[i]);
        }

        if(mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));

        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            current = position;
            if(position == 0){



            } else if(position == mDots.length - 1){


                insertDataToDb();
                sp.edit().putBoolean("isChecked",true).apply();
                Intent intent= new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
                finish();

            } else {

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public  void  insertDataToDb(){
        db.insert("Headache,AbdominalPain,Diarrhea,","Typhoid","Typhoid fever is caused by Salmonella typhi bacteria. Typhoid fever is rare in industrialized countries. However, it remains a serious health threat in the developing world, especially for children.\n" +
                "\n" +
                "Typhoid fever spreads through contaminated food and water or through close contact with someone who's infected. Signs and symptoms usually include a high fever, headache, abdominal pain, and either constipation or diarrhea.\n" +
                "\n" +
                "Most people with typhoid fever feel better within a few days of starting antibiotic treatment, although a small number of them may die of complications. Vaccines against typhoid fever are available, but they're only partially effective. Vaccines usually are reserved for those who may be exposed to the disease or are traveling to areas where typhoid fever is common.","Typhoid fever is caused by Salmonella typhi bacteria. Typhoid fever is rare in industrialized countries. However, it remains a serious health threat in the developing world, especially for children.");
        db.insert("Cough,Fever,Breath,","Corona COVID-19","Coronavirus disease (COVID-19) is an infectious disease caused by a new virus.\n" +
                "The disease causes respiratory illness (like the flu) with symptoms such as a cough, fever, and in more severe cases, difficulty breathing. You can protect yourself by washing your hands frequently, avoiding touching your face, and avoiding close contact (1 meter or 3 feet) with people who are unwell.","Coronavirus disease (COVID-19) is an infectious disease caused by a new virus.");

        db.insert("Thirst,Urination,Hunger,","Diabetes","Diabetes is a disease that occurs when your blood glucose, also called blood sugar, is too high. Blood glucose is your main source of energy and comes from the food you eat. Insulin, a hormone made by the pancreas, helps glucose from food get into your cells to be used for energy. Sometimes your body doesn’t make enough—or any—insulin or doesn’t use insulin well. Glucose then stays in your blood and doesn’t reach your cells.\n" +
                "\n" +
                "Over time, having too much glucose in your blood can cause health problems. Although diabetes has no cure, you can take steps to manage your diabetes and stay healthy.\n" +
                "\n" +
                "Sometimes people call diabetes “a touch of sugar” or “borderline diabetes.” These terms suggest that someone doesn’t really have diabetes or has a less serious case, but every case of diabetes is serious.","Diabetes is a disease that occurs when your blood glucose, also called blood sugar, is too high. Blood glucose is your main source of energy and comes from the food you eat. Insulin, a hormone made by the pancreas, helps glucose from food get into your cells to be used for energy.");
    }


}
