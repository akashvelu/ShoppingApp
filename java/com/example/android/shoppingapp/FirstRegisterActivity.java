package com.example.android.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FirstRegisterActivity extends AppCompatActivity {

    private EditText passwordET;
    private EditText confirmPasswordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_register);
    }
    public void nextScreen (View view) {
        Context context = FirstRegisterActivity.this;
        Class destinationActivity = SecondRegisterActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        //startActivity(intent);

        passwordET = (EditText) findViewById(R.id.etPassword);
        confirmPasswordET = (EditText) findViewById(R.id.etConfirmPassword);

        String password = (passwordET.getText()).toString();
        String confirmPassword = (confirmPasswordET.getText()).toString();


        if (password.equals(confirmPassword)) {
            intent.putExtra("firstName", ((EditText) findViewById(R.id.etFirstName)).getText().toString());
            intent.putExtra("lastName",((EditText) findViewById(R.id.etLastName)).getText().toString());
            intent.putExtra("email",((EditText) findViewById(R.id.etEmail)).getText().toString());
            intent.putExtra("password",((EditText) findViewById(R.id.etPassword)).getText().toString());
            startActivity(intent);
        }
        else {
            Toast passwordToast = Toast.makeText(FirstRegisterActivity.this, "Passwords Don't Match", Toast.LENGTH_SHORT);
            passwordToast.show();
        }


    }


}
