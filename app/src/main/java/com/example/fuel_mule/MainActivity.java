package com.example.fuel_mule;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements ToolbarFragment.Callbacks {

    private static final String EXTRA_USERNAME = "com.example.fuel_mule.username";

    public static Intent newIntent(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        Log.d("Test", "MainActivity Created");

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

    @Override
    // button must be: "h", "c", "l" or "u"
    public void onMenuButtonSelected(String button) {

        Log.d("Test", "Menu Button Selected: " + button);

        Fragment display = new DisplayFragment();

        Bundle args = new Bundle();
        args.putString("buttonChar", button);
        display.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.display_container, display).commit();
    }
}
