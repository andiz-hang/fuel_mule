package com.example.fuel_mule;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements ToolbarFragment.Callbacks {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment display = new DisplayFragment();
        fm.beginTransaction().add(R.id.display_container, display).commit();

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
