package com.example.dietmanagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TuesdayFragment extends Fragment {
    TextView t1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tuesday, container, false);

        t1 = (TextView) v.findViewById(R.id.day);
        Bundle bndTue = getArguments();
        String day = bndTue.getString("tuesday");
        t1.setText(day);
        return v;
    }
}