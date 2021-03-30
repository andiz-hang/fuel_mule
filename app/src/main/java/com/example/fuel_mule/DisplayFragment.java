package com.example.fuel_mule;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

public class DisplayFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        @LayoutRes int fragID;

        if (bundle == null) {
            fragID = R.layout.fragment_home;
        } else {
            String buttonChar = bundle.getString("buttonChar");

            if (buttonChar == null) throw new RuntimeException("Error: buttonChar is null");

            switch (buttonChar) {
                case ("h"):
                    fragID = R.layout.fragment_home;
                    break;
                case ("c"):
                    fragID = R.layout.fragment_camera;
                    break;
                case ("l"):
                    fragID = R.layout.fragment_logs;
                    break;
                case ("u"):
                    fragID = R.layout.fragment_user;
                    break;
                default:
                    throw new RuntimeException("Error: buttonChar is not h, c, l, or u.");
            }
        }

        View v = inflater.inflate(fragID, container, false);

        return v;
    }
}
