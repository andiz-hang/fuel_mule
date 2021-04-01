package com.example.fuel_mule;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements ToolbarFragment.Callbacks, CameraFragment.Callbacks {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }

    // Display the correct page depending on the menu button clicked
    private Fragment displayPage(String b) {

        switch (b) {
            case "h":
                return new HomeFragment();
            case "c":
                return new CameraFragment();
            case "cr":
                return new CameraResultsFragment();
            case "l":
                return new LogsFragment();
            case "u":
                return new UserFragment();
            default:
                throw new RuntimeException("Error: buttonChar is not h, c, l, or u.");
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment display = new HomeFragment();
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

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.display_container, displayPage(button))
                .commit();
    }

    @Override
    public void onAnalyzeButtonSelected() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.display_container, new CameraResultsFragment())
                .commit();
    }
}
