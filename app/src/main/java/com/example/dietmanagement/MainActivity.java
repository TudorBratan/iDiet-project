package com.example.dietmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String current_user = i.getStringExtra("current_user");
        TextView current_user_txtview = findViewById(R.id.current_user);
        current_user_txtview.setText("Welcome, " + current_user);
    }

}