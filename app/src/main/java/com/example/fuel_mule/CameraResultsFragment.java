package com.example.fuel_mule;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CameraResultsFragment extends DisplayFragment {

    private static final @LayoutRes int ResID = R.layout.fragment_camera_results;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        return v;
    }
}