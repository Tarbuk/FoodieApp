package com.example.thomas.foodie;

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

public class ResListActivity extends AppCompatActivity {

    SessionManager session;

    ListView res_list;
    Adapter adapter;
    HttpURLConnection urlConnection;
    ArrayList<HashMap<String, String>> restaurantList;
    JSONArray restaurants = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_list);


        restaurantList = new ArrayList<HashMap<String, String>>();

        //HashMap<String, String> user = session.getUserDetails();

     //   name = user.get(SessionManager.KEY_NAME);

     //   email = user.get(SessionManager.KEY_EMAIL);

     //   token = user.get(SessionManager.KEY_TOKEN);

        final StringBuilder result = new StringBuilder();

        res_list = (ListView) findViewById(R.id.list);
        res_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String resId = ((TextView) view.findViewById(R.id.id))
                        .getText().toString();
                String resLat = ((TextView) view.findViewById(R.id.lat))
                        .getText().toString();
                String resLng = ((TextView) view.findViewById(R.id.lng))
                        .getText().toString();
                String resdesc = ((TextView) view.findViewById(R.id.desc))
                        .getText().toString();


                Intent i = new Intent(ResListActivity.this, ResDetailsActivity.class);
                i.putExtra("id",resId);
                i.putExtra("name",name);
                i.putExtra("lat",resLat);
                i.putExtra("lng",resLng);
                i.putExtra("desc",resdesc);

                startActivity(i);
            }
        });

//        ArrayAdapter<Restaurant> adapter = new ArrayAdapter<Restaurant>(this,
  //              R.layout.res_item, items);
//        res_list.setAdapter(adapter);

        GetRestaurants Res = new GetRestaurants();
        Res.execute();


    }

    public class GetRestaurants extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            StringBuilder result = new StringBuilder();
            HttpURLConnection urlConnection;

            try {
                URL url = new URL("http://foodiebjtu.herokuapp.com/restaurant/list");

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

                        int id = c.getInt("id");
                        String place = c.getString("address");
                        String desc = c.getString("description");
                        String name = c.getString("name");
                        int owner = c.getInt("owner_id");
                        int seats = c.getInt("seats");
                        double lng = c.getDouble("longitude");
                        double lat = c.getDouble("latitude");


                     HashMap<String, String> restaurant = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        restaurant.put("id", ""+ id);
                        restaurant.put("address", place);
                        restaurant.put("description", "description:"+desc);
                        restaurant.put("name", name);
                        restaurant.put("owner_id",""+owner);
                        restaurant.put("seats", "number of seats: "+seats);
                        restaurant.put("longitude", "lng: "+lng);
                        restaurant.put("latitude", "lat: "+lat);
                        //contact.put(TAG_ID, id);
                        //contact.put(TAG_NAME, name);
                        //ontact.put(TAG_EMAIL, email);
                        // contact.put(TAG_PHONE_MOBILE, mobile);

                        restaurantList.add(restaurant);
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
                               ResListActivity.this, restaurantList,
                              R.layout.res_item, new String[] {"id", "name", "address",
                             "seats", "latitude", "longitude", "description" }, new int[] {R.id.id ,R.id.name, R.id.address, R.id.seats, R.id.lat, R.id.lng, R.id.desc });
            res_list.setAdapter(adapter);
        }

    }

}


