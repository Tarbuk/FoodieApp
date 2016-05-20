package com.example.thomas.foodie;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.json.*;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends Activity  {
    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.bregister);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        session = new SessionManager(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(ed1.getText().toString().equals("admin") &&

                        ed2.getText().toString().equals("admin")) {
                    String token = "123456789";
                    //session.createLoginSession(ed1.toString(), ed2.toString(), token);
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, ResListActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


    }



    //   @Override
   // public boolean onCreateOptionsMenu(Menu menu) {
     //   // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
      //  return true;
  //  }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
  //          return true;
    //    }
        return super.onOptionsItemSelected(item);
    }
}

