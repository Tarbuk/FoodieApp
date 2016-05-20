package com.example.thomas.foodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ResDetailsActivity extends AppCompatActivity {

    ImageButton bMap;
    ImageButton bMenu;
    ImageButton bReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_details);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String Did = myIntent.getStringExtra("id"); // will return "FirstKeyValue"
        final String Dname = myIntent.getStringExtra("name");
       final String Daddress = myIntent.getStringExtra("address");
        final String Dlat = myIntent.getStringExtra("lat");
        final String Dlng = myIntent.getStringExtra("lng");
       final String Ddesc = myIntent.getStringExtra("desc");

        TextView tId =(TextView)findViewById(R.id.did);
        TextView tName =(TextView)findViewById(R.id.dname);
        TextView tAddr =(TextView)findViewById(R.id.daddress);
        TextView tLat=(TextView)findViewById(R.id.dlat);
        TextView tLng=(TextView)findViewById(R.id.dlng);
        TextView tDesc =(TextView)findViewById(R.id.ddesc);

        tId.setText(Did);
        tName.setText(Dname);
        tAddr.setText(Daddress);
        tLat.setText(Dlat);
        tLng.setText(Dlng);
        tDesc.setText(Ddesc);


        bMap =(ImageButton)findViewById(R.id.imageButton);
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResDetailsActivity.this, ResMapActivity.class);
                i.putExtra("lat", Dlat);
                i.putExtra("lng", Dlng);
                startActivity(i);
            }
        });

        bMenu =(ImageButton)findViewById(R.id.imageButton2);
        bMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResDetailsActivity.this, ResDishActivity.class);
                i.putExtra("id",Did);
                i.putExtra("name",Dname);
                i.putExtra("lat",Dlat);
                i.putExtra("lng",Dlng);
                i.putExtra("desc",Ddesc);
                startActivity(i);
            }
        });


        bReservation =(ImageButton)findViewById(R.id.imageButton3);
        bReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You download is resumed",Toast.LENGTH_LONG).show();
        Intent i = new Intent(ResDetailsActivity.this, ReservationActivity.class);
            }
        });

    }
}
