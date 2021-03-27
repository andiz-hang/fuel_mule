package com.example.fuel_mule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private String mUsername;
//    private String mPassword; #NOT NEEDED FOR THE DEMO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername = ;

                Intent intent = MainActivity.newIntent(LoginActivity.this, mUsername);
                startActivity(intent);
            }
        });
    }
}
