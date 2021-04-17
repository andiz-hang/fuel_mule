package com.example.fuel_mule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class ToolbarFragment extends Fragment {

    private FrameLayout mSelectedFrame;

    private ImageButton mButtonHome;
    private ImageButton mButtonCamera;
    private ImageButton mButtonUser;

    private FrameLayout mFrameButtonHome;
    private FrameLayout mFrameButtonCamera;
    private FrameLayout mFrameButtonUser;

    private Callbacks mCallbacks;
    public interface Callbacks {
        void onMenuButtonSelected(String button);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    // Revert the colour of the current selected button and change the colour of the selected button
    private void changeButtonColour(FrameLayout newFrame) {
        if (mSelectedFrame == newFrame) return;

        mSelectedFrame.setBackgroundResource(R.color.colorPrimary);
        newFrame.setBackgroundResource(R.color.colorSelected);

        mSelectedFrame = newFrame;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_toolbar, container, false);

        mFrameButtonHome = v.findViewById(R.id.home_button_frame);
        mFrameButtonCamera = v.findViewById(R.id.camera_button_frame);
        mFrameButtonUser = v.findViewById(R.id.user_button_frame);

        // Default selected button is the HOME button
        mSelectedFrame = mFrameButtonHome;

        // HOME BUTTON
        mButtonHome = v.findViewById(R.id.home_button);
        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "HOME");

                changeButtonColour(mFrameButtonHome);
                mCallbacks.onMenuButtonSelected("h");
            }
        });

        // CAMERA BUTTON
        mButtonCamera = v.findViewById(R.id.camera_button);
        mButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "CAMERA");

                changeButtonColour(mFrameButtonCamera);
                mCallbacks.onMenuButtonSelected("c");
            }
        });

        // USER BUTTON
        mButtonUser = v.findViewById(R.id.user_button);
        mButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "USER");

                changeButtonColour(mFrameButtonUser);
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
