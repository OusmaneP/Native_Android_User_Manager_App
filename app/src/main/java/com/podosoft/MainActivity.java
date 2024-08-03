package com.podosoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_sign_in, btn_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Layout
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in = findViewById(R.id.btn_sign_in);


        // Buttons Sign Up and In Clicked
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignIn();
            }
        });

    }

    // End Of OnCreate Method


    // Sign Up Go to
    public void goToSignUp(){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);

    }

    //Sign In Go To
    public void goToSignIn(){
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);

    }


}