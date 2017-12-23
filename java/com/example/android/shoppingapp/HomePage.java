package com.example.android.shoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity implements AsyncResponse {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    private List<ListItem> listItems;
    final String URL = "https://n29d52czu8.execute-api.us-west-1.amazonaws.com/prod/shoppingrequests/getallrequests";
    private JSONObject recieved;
    private JSONArray items;


    public void processFinish(String[] output) {
        Log.i("RECIEVED 1", output[0]);
        try {
            recieved = new JSONObject(output[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            items = recieved.getJSONArray("Items");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.i("ITEMS", items.toString());

    }

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", "blah");
        params.put("password", "blah");


        postRequest postRequest = new postRequest(URL, params);
        postRequest.delegate = this;
        postRequest.execute();

        listItems = new ArrayList<>();



        for (int i = 0; i<=20; i++) {
            ListItem listItem = new ListItem(
                    "heading " + (i+1),
                    "Lorem ipsum blah"
            );

            listItems.add(listItem);
        }


        //Log.i("ITEMS2", items.toString());

/*
        JSONObject currentObject = null;
        ListItem listItem = null;
        for (int i = 0; i < items.length(); i++) {
            try {
                currentObject = items.getJSONObject(i);
                Log.i(String.valueOf(i), currentObject.toString());
                listItem = new ListItem(
                        currentObject.getString("ID"),
                        currentObject.getString("Description")
                );

            } catch (JSONException e) {
                e.printStackTrace();
            }

            listItems.add(listItem);
        }*/

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }
}
