package com.example.android.shoppingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akashvelu on 6/28/17.
 */

public class User  {
    private static int id = 0;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String state;
    public int statusCode;

    public User (String email, String firstName, String lastName, String password, String address, String state) {
        id++;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.state = state;

    }



    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public String getAddress() {
        return address;
    }
    public String getState() {
        return state;
    }
    //public boolean getRegisterSuccess(){return registerSuccess;}

}
