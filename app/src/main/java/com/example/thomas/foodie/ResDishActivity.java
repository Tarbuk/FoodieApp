package com.example.thomas.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ResDishActivity extends AppCompatActivity {

    SessionManager session;

    ListView res_list;
    Adapter adapter;
    HttpURLConnection urlConnection;
    ArrayList<HashMap<String, String>> dishList;
    JSONArray restaurants = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_list);


        Intent myIntent = getIntent(); // gets the previously created intent
        final String Did = myIntent.getStringExtra("id"); // will return "FirstKeyValue"
        String Dname = myIntent.getStringExtra("name");
        String Daddress = myIntent.getStringExtra("address");
        final String Dlat = myIntent.getStringExtra("lat");
        final String Dlng = myIntent.getStringExtra("lng");
        String Ddesc = myIntent.getStringExtra("desc");


        dishList = new ArrayList<HashMap<String, String>>();

        //HashMap<String, String> user = session.getUserDetails();

        //   name = user.get(SessionManager.KEY_NAME);

        //   email = user.get(SessionManager.KEY_EMAIL);

        //   token = user.get(SessionManager.KEY_TOKEN);

        final StringBuilder result = new StringBuilder();

        res_list = (ListView) findViewById(R.id.list);
//        ArrayAdapter<Restaurant> adapter = new ArrayAdapter<Restaurant>(this,
        //              R.layout.res_item, items);
//        res_list.setAdapter(adapter);

        GetDishes Res = new GetDishes();
        Res.execute(Did);


    }

    public class GetDishes extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg0) {

            StringBuilder result = new StringBuilder();
            HttpURLConnection urlConnection;

            try {
                URL url = new URL("http://foodiebjtu.herokuapp.com/dish/list/"+arg0[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod( "GET" );

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            }catch( Exception e) {
                e.printStackTrace();
            }

            String jsonStr = result.toString();

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    restaurants = jsonObj.getJSONArray("list");

                    // looping through All Contacts
                    for (int i = 0; i < restaurants.length(); i++) {
                        JSONObject c = restaurants.getJSONObject(i);

                        String desc = c.getString("description");
                        int id = c.getInt("id");
                        int res_id = c.getInt("id_restaurant");
                        String name = c.getString("name");
                        double price = c.getDouble("price");


                        HashMap<String, String> dish = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        dish.put("id", ""+ id);
                        dish.put("name", name);
                        dish.put("description", "description:"+desc);
                        dish.put("price", "Price:"+price);

                        //contact.put(TAG_ID, id);
                        //contact.put(TAG_NAME, name);
                        //ontact.put(TAG_EMAIL, email);
                        // contact.put(TAG_PHONE_MOBILE, mobile);

                        dishList.add(dish);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            /**
             * Updating parsed JSON data into ListView
             * */


//            res_list = (ListView) findViewById(R.id.list_res);
            ListAdapter adapter = new SimpleAdapter(
                    ResDishActivity.this, dishList,
                    R.layout.res_dish_item, new String[] {"id", "name", "description", "price"}, new int[] {R.id.did ,R.id.dname, R.id.ddesc, R.id.dprice });
            res_list.setAdapter(adapter);
        }

    }

}


