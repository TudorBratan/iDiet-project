package com.example.dietmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DayFragment extends Fragment {

    private TextView dayTV;
    private ArrayList<String> hour_food;
    private ArrayAdapter<String> listViewAdapter;
    private ListView listView;
    private EditText input_meal;
    private EditText input_time;
    private Button submit;
    private String text;

    public DayFragment(String text) {
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day, container, false);

        dayTV = v.findViewById(R.id.day);
        dayTV.setText(text);

        hour_food = new ArrayList<>();
        listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, hour_food);
        listView = v.findViewById(R.id.list_item);

        listView.setAdapter(listViewAdapter);

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            hour_food.remove(position);
            Toast.makeText(getActivity(), "Meal Removed", Toast.LENGTH_SHORT).show();
            listViewAdapter.notifyDataSetChanged();
            return true;
        });

        input_meal = v.findViewById(R.id.input_meal);
        input_time = v.findViewById(R.id.input_time);

        submit = v.findViewById(R.id.submit_food_btn);
        submit.setOnClickListener(v1 -> {
            final String time, meal, day;
            meal = String.valueOf(input_meal.getText());
            time = String.valueOf(input_time.getText());
            day = String.valueOf(dayTV.getText());

            if(!time.equals("") && !meal.equals("")) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[3];
                        field[0] = "time"; field[1] = "meal"; field[2] = "day";
                        String[] data = new String[3];
                        data[0] = time; data[1] = meal; data[2] = day;
                        PutData putData = new PutData("http://192.168.1.105/LoginRegister/listcontent.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if(result.equals("Item Add Success")) {
                                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                                    if (TextUtils.isEmpty(input_time.getText())) {
                                        Toast.makeText(getActivity(), "Empty time input", Toast.LENGTH_SHORT).show();
                                    } else if (TextUtils.isEmpty(input_meal.getText())) {
                                        Toast.makeText(getActivity(), "Empty meal input", Toast.LENGTH_SHORT).show();
                                    } else {
                                        hour_food.add(String.format("%s - %s", input_meal.getText().toString(), input_time.getText().toString()));
                                        listViewAdapter.notifyDataSetChanged();
                                        input_meal.setText("");
                                        input_time.setText("");
                                    }
                                }
                                else {
                                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                                    Log.d("mak", result);
                                }
                            }
                        }
                    }
                });
            }
        });

        getdata();
        /*loadList = (Button)v.findViewById(R.id.load_list);
        loadList.setOnClickListener(v2 -> {
            getdata();
        });
        */
        return v;
    }

    private static final String TAG = MainActivity.class.getSimpleName();
    //private static final String url = "http://192.168.1.105/LoginRegister/retrievelistcontent.php";

    public void getdata() {
        String url = "http://192.168.1.105/LoginRegister/retrievelistcontent.php";
        JsonObjectRequest listReq = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        for (int i = 0; i < response.toString().length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(String.valueOf(i));
                                hour_food.add(String.format("%s - %s", obj.getString("time"), obj.getString("meal")));
                                listViewAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, error.getMessage());
                        Log.d("TAG", error.getMessage());
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        AppController.getInstance().addToRequestQueue(listReq);

    }
}

/*

Log.d(TAG, response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        hour_food.add(String.format("%s - %s", obj.getString("time"), obj.getString("meal")));
                        listViewAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                // notifying list adapter about data changes so that it renders the list view with updated data
                listViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, error.getMessage());
                Log.d("TAG", error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }

            // Adding request to request queue
        AppController.getInstance().addToRequestQueue(listReq);
 */




/*
        RequestQueue mRequestQueue = Volley.newRequestQueue(requireContext());
        String url = "http://192.168.1.105/LoginRegister/retrievelistcontent.php";
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                for(int i = 0; i < response.length(); i++) {
                    hour_food.add(response);
                    listViewAdapter.notifyDataSetChanged();
                    //Toast.makeText(getActivity(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("TAG", "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
 */
