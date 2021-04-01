package com.example.fuel_mule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressWarnings("FieldCanBeLocal")
public class CameraFragment extends DisplayFragment {
    private static final @LayoutRes int ResID = R.layout.fragment_camera;

    private Button mButtonAnalyze;
    private Button mButtonTakePhoto;
    private Button mButtonImportPhoto;

    private Callbacks mCallbacks;
    public interface Callbacks {
        void onAnalyzeButtonSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mButtonAnalyze = v.findViewById(R.id.analyze_button);
        mButtonAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onAnalyzeButtonSelected();
            }
        });

        mButtonTakePhoto = v.findViewById(R.id.take_photo_button);
        mButtonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Camera
            }
        });

        mButtonImportPhoto = v.findViewById(R.id.import_photo_button);
        mButtonImportPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Files
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
