package com.example.android.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;


public class SecondRegisterActivity extends AppCompatActivity implements AsyncResponse {

    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    String state;

    final String REGISTER_URL = "https://n29d52czu8.execute-api.us-west-1.amazonaws.com/prod/users/register";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register);
    }

    public void processFinish(String[] output) {
        int statusCode = Integer.parseInt(output[0]);

        if (statusCode == 200) {
            Toast toast = Toast.makeText(this, "User successfully created", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent (this, HomePage.class);
            startActivity(intent);


        }
        else if (statusCode == 201) {
            Toast toast = Toast.makeText(this, "User already exists", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "Error in creating user", Toast.LENGTH_LONG);
            toast.show();
        }

    }






    //when register button is clicked
    public void registerToHome(View view) {

        Bundle bundle = getIntent().getExtras();
        firstName = bundle.getString("firstName");
        lastName = bundle.getString("lastName");
        email = bundle.getString("email");
        password = bundle.getString("password");
        address = ((EditText) findViewById(R.id.etAddress)).getText().toString();
        state = ((Spinner) findViewById(R.id.spinnerState)).getSelectedItem().toString();

        Map<String, String> params = new HashMap<String, String>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("email", email);
        params.put("password", password);
        params.put("address", address);
        params.put("state", state);

        postRequest postRequest = new postRequest(REGISTER_URL, params);
        postRequest.delegate = this;
        postRequest.execute();

    }

}
