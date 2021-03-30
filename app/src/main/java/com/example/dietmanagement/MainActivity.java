package com.example.dietmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button monday = findViewById(R.id.monday_btn),
                tuesday = findViewById(R.id.tuesday_btn),
                wednesday = findViewById(R.id.wednesday_btn),
                thursday = findViewById(R.id.thursday_btn),
                friday = findViewById(R.id.friday_btn),
                saturday = findViewById(R.id.saturday_btn),
                sunday = findViewById(R.id.sunday_btn);

        openFragment(monday);
        openFragment(tuesday);
        openFragment(wednesday);
        openFragment(thursday);
        openFragment(friday);
        openFragment(saturday);
        openFragment(sunday);
    }

    private void openFragment(Button btn) {
        btn.setOnClickListener(v -> {
            String contentDescription = btn.getContentDescription().toString();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.daysfragment, new DayFragment(contentDescription))
                    .addToBackStack(null)
                    .commit();
        });
    }

}