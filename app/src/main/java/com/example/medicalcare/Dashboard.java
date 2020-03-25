package com.example.medicalcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TimePicker;

import com.example.medicalcare.Fragment.DiagnosisFragment;
import com.example.medicalcare.Fragment.MedicneAlarmFragment;
import com.example.medicalcare.Fragment.TipFragment;
import com.example.medicalcare.Helper.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private DiagnosisFragment diagnosisFragment;
    private TipFragment tipFragment;
    private MedicneAlarmFragment firstAidFragment;
    MedicneAlarmFragment md;
    int flag=0;

    public int getFlag() {
        return flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);
        md= new MedicneAlarmFragment();

        diagnosisFragment = new DiagnosisFragment();
        tipFragment = new TipFragment();
        firstAidFragment = new MedicneAlarmFragment();


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_tip :
                        setFragment(tipFragment);
                        return true;

                    case R.id.nav_diagnosis :
                        setFragment(diagnosisFragment);
                        return true;

                    case R.id.nav_firstaid :
                        setFragment(firstAidFragment);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        flag = 1;
        DatabaseHelper db= new DatabaseHelper(this);
        db.insertalarm(md.getName(),hourOfDay,minute);
        flag = 0;
    }
}
