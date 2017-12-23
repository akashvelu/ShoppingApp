package com.example.android.shoppingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by akashvelu on 7/28/17.
 */

public class postRequest extends AsyncTask<Void, Void, String[]> {

    private String strUrl;
    private JSONObject data;
    public AsyncResponse delegate = null;



    public postRequest(String s, Map<String, String> m) {
        this.strUrl = s;
        this.data = new JSONObject(m);

    }
    @Override

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String[] res) {
        delegate.processFinish(res);
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        String status = "a";
        String response = "";
        String [] result = new String[2];
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(data.toString());
            writer.flush();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }

            int statusCode = conn.getResponseCode();


            response = sb.toString();
            status = String.valueOf(conn.getResponseCode());
            Log.d("Status", status);
            result[0] = status;
            result[1] = response;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Log.d("status", status);
        return result;
    }


}
