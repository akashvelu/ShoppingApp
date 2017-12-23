package com.example.android.shoppingapp;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by akashvelu on 8/24/17.
 */

public class getRequest extends AsyncTask <Void, Void, String[]> {

    String strUrl;
    public AsyncResponse delegate = null;


    public getRequest(String s) {
        this.strUrl = s;
    }

    protected void onPreExecute(String[] res) {
        delegate.processFinish(res);
    }

    @Override
    protected void onPostExecute(String[] s) {
        super.onPostExecute(s);
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        String[] result = new String[2];
        String status;
        String res = "";

        try {
            URL url = new URL (strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }

            int statusCode = conn.getResponseCode();
            res = sb.toString();
            status = String.valueOf(statusCode);

            result[0] = status;
            result[1] = res;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
