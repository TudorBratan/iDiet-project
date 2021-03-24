package com.example.dietmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    final MondayFragment mondayFragment = new MondayFragment();
    final TuesdayFragment tuesdayFragment = new TuesdayFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String current_user = i.getStringExtra("current_user");
        TextView current_user_txtview = findViewById(R.id.current_user);
        current_user_txtview.setText("Welcome, " + current_user);

        monday = (Button)findViewById(R.id.monday_btn);
        tuesday = (Button)findViewById(R.id.tuesday_btn);
        wednesday = (Button)findViewById(R.id.wednesday_btn);
        thursday = (Button)findViewById(R.id.thursday_btn);
        friday = (Button)findViewById(R.id.friday_btn);
        saturday = (Button)findViewById(R.id.saturday_btn);
        sunday = (Button)findViewById(R.id.sunday_btn);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(mondayFragment);
                Bundle bndMon = new Bundle();
                bndMon.putString("monday", (String) monday.getContentDescription());
                mondayFragment.setArguments(bndMon);
            }
        });


        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(tuesdayFragment);
                Bundle bndTue = new Bundle();
                bndTue.putString("tuesday", (String) tuesday.getContentDescription());
                tuesdayFragment.setArguments(bndTue);
            }
        });

    }

    private void openFragment(final Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.daysfragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}