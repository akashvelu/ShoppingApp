package com.example.android.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    String email;
    String password;

    final String LOGIN_URL = "https://n29d52czu8.execute-api.us-west-1.amazonaws.com/prod/users/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void processFinish(String[] output) {



        int statusCode = Integer.valueOf(output[0]);
        //Toast toast = Toast.makeText(this, String.valueOf(statusCode), Toast.LENGTH_LONG);
        //toast.show();
        Log.i("INFOSTATUS", output[1]);


        //statusCode 200 means successful POST request
        if (statusCode == 200) {
            Toast toast = Toast.makeText(this, "Successful login", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent (this, HomePage.class);
            startActivity(intent);
        }
        //statusCode 209 is a created Code to indicate that user does not exist
        else if (statusCode == 209) {
            Toast toast = Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG);
            toast.show();
        }
        //statusCode 201 is a created Code to indicate that the password is incorrect
        else if (statusCode == 201) {
            Toast toast = Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG);
            toast.show();
        }

        else {
            Toast toast = Toast.makeText(this, "Error in logging in", Toast.LENGTH_LONG);
            toast.show();
        }

    }






    public void loginToHome(View view) {
        email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
        password = ((EditText) findViewById(R.id.etPassword)).getText().toString();

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);

        postRequest postRequest = new postRequest(LOGIN_URL, params);
        postRequest.delegate = this;
        postRequest.execute();

    }

    public void loginToRegister (View view) {
        Context context = LoginActivity.this;
        Class destinationActivity = FirstRegisterActivity.class;
        Intent intent = new Intent (context, destinationActivity);
        startActivity(intent);
    }


}
