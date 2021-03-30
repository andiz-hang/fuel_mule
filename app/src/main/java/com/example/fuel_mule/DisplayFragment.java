package com.example.fuel_mule;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

public class DisplayFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final @LayoutRes int HOME = R.layout.fragment_home;
        final @LayoutRes int CAMERA = R.layout.fragment_camera;
        final @LayoutRes int LOGS = R.layout.fragment_logs;
        final @LayoutRes int USER = R.layout.fragment_user;

        Bundle bundle = this.getArguments();
        @LayoutRes int fragID;

        if (bundle == null) {
            fragID = HOME;
        } else {
            String buttonChar = bundle.getString("buttonChar");

            if (buttonChar == null) fragID = HOME;
            else {
                switch (buttonChar) {
                    case ("h"):
                        fragID = HOME;
                        break;
                    case ("c"):
                        fragID = CAMERA;
                        break;
                    case ("l"):
                        fragID = LOGS;
                        break;
                    case ("u"):
                        fragID = USER;
                        break;
                    default:
                        throw new RuntimeException("Error: buttonChar is not h, c, l, or u.");
                }
            }
        }

        View v = inflater.inflate(fragID, container, false);

        return v;
    }
}
