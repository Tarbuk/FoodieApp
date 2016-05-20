package com.example.thomas.foodie;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Thomas on 18/05/2016.
 */
public class getData extends AsyncTask<String, String, String> {

    HttpURLConnection urlConnection;
    public interface AsyncResponse {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;

    public Void MyAsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
        return null;
    }

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("http://foodiebjtu/herokuapp.com/list");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {

        //Do something with the JSON string

    }

}