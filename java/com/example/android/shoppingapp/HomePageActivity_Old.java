package com.example.android.shoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity_Old extends AppCompatActivity implements AsyncResponse {

    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;

    private final String REQUEST_URL = "https://n29d52czu8.execute-api.us-west-1.amazonaws.com/prod/shoppingrequests/getallrequests";

    private List<ListItem> listItems;

    public void processFinish(String[] output) {

        listItems = new ArrayList<>();

        String jsonString = output[1];
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray array = jsonObject.getJSONArray("Items");

            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                ListItem item = new ListItem(String.valueOf(o.getInt("ID")), o.getString("Description"));

                listItems.add(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //every item of recyclerview has a fixed size
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        getRequest getRequest = new getRequest(REQUEST_URL);








    }
}
