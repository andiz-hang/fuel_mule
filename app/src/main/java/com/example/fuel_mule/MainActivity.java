package com.example.fuel_mule;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_USERNAME = "com.example.fuel_mule.username";

    public static Intent newIntent(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment display = fm.findFragmentById((R.id.display_container));
        if (display == null) {
            display = new DisplayFragment();
            fm.beginTransaction().add(R.id.display_container, display).commit();
        }

        Fragment toolbar = fm.findFragmentById(R.id.toolbar_container);
        if (toolbar == null) {
            toolbar = new ToolbarFragment();
            fm.beginTransaction().add(R.id.toolbar_container, toolbar).commit();
        }
    }
}
