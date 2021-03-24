package com.example.dietmanagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MondayFragment extends Fragment {

    public TextView mondayTV;
    public ArrayList<String> hour_food;
    public ArrayAdapter<String> listViewAdapter;
    public ListView listView;
    public EditText input_meal;
    public Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_monday, container, false);

        mondayTV = (TextView) v.findViewById(R.id.day);
        Bundle bndMon = getArguments();
        String day = bndMon.getString("monday");
        mondayTV.setText(day);

        hour_food = new ArrayList<String>();
        listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, hour_food);
        listView = (ListView)v.findViewById(R.id.monday_list_item);

        listView.setAdapter(listViewAdapter);

        input_meal = v.findViewById(R.id.input_meal);

        submit = (Button) v.findViewById(R.id.submit_food_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour_food.add(input_meal.getText().toString());
                listViewAdapter.notifyDataSetChanged();
                input_meal.setText("");
            }
        });

        return v;
    }
}