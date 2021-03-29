package com.example.fuel_mule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ToolbarFragment extends Fragment {

    private ImageButton mButtonHome;
    private ImageButton mButtonCamera;
    private ImageButton mButtonLogs;
    private ImageButton mButtonUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_toolbar, container, false);

        // HOME BUTTON
        mButtonHome = v.findViewById(R.id.home_button);
        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send data to change display to HOME
            }
        });

        // CAMERA BUTTON
        mButtonCamera = v.findViewById(R.id.camera_button);
        mButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //
        mButtonLogs = v.findViewById(R.id.logs_button);
        mButtonLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonUser = v.findViewById(R.id.user_button);
        mButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}
