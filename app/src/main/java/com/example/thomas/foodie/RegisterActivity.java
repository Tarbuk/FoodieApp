package com.example.thomas.foodie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    EditText edfirst, edlast, edpass, edmail;
    Button blog, blink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.activity_register);

        blog=(Button)findViewById(R.id.btnRegister);
        blink=(Button)findViewById(R.id.link_to_login);


        edfirst=(EditText)findViewById(R.id.reg_firstname);
        edlast=(EditText)findViewById(R.id.reg_lastname);
        edpass=(EditText)findViewById(R.id.reg_password);
        edmail=(EditText)findViewById(R.id.reg_email);

        blog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {




                                    }
                                });
        // Listening to Login Screen link
        blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();



            }
        });
    }



}