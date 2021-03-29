package com.example.fuel_mule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private EditText mUsernameField;
//    private EditText mPasswordField; #NOT NEEDED FOR THE DEMO
    private String mUsername;
//    private String mPassword; #NOT NEEDED FOR THE DEMO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = findViewById(R.id.username_textbox);
        mUsernameField.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              mUsername = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
              // This one too
            }
        });

//        mPasswordField = findViewById(R.id.password_textbox);

        mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = MainActivity.newIntent(LoginActivity.this, mUsername);
            startActivity(intent);
            }
        });
    }
}
