package com.example.dietmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Wrong_label;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.login_user_input);
        Password = (EditText)findViewById(R.id.login_pass_input);
        Button login = (Button) findViewById(R.id.login_button);
        Wrong_label = (TextView)findViewById(R.id.wrong_label);
        TextView register = (TextView)findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    public void validate(String user_input, String pass_input) {
        if((user_input.equals("TudorBratan")) && (pass_input.equals("admin"))){
            Intent log = new Intent(this, MainActivity.class);
            log.putExtra("current_user", user_input);
            startActivity(log);
        }
        else
            Wrong_label.setText("Invalid credentials");
    }

    public void register()
    {
        Intent reg = new Intent(this, RegisterActivity.class);
        startActivity(reg);
    }
}