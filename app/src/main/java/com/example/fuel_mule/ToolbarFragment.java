package com.example.fuel_mule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    private Callbacks mCallbacks;
    public interface Callbacks {
        void onMenuButtonSelected(String button);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

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
                Log.d("TEST", "HOME");
                mCallbacks.onMenuButtonSelected("h");
            }
        });

        // CAMERA BUTTON
        mButtonCamera = v.findViewById(R.id.camera_button);
        mButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "CAMERA");
                mCallbacks.onMenuButtonSelected("c");
            }
        });

        // LOGS BUTTON
        mButtonLogs = v.findViewById(R.id.logs_button);
        mButtonLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "LOGS");
                mCallbacks.onMenuButtonSelected("l");
            }
        });

        // USER BUTTON
        mButtonUser = v.findViewById(R.id.user_button);
        mButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "USER");
                mCallbacks.onMenuButtonSelected("u");
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
