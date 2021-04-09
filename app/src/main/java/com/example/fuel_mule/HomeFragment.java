package com.example.fuel_mule;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends DisplayFragment {
    private static final @LayoutRes int ResID = R.layout.fragment_home;

    private RecyclerView mQuickAddMenu;

//    private class Quickadd

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mQuickAddMenu = v.findViewById(R.id.quick_add);

        return v;
    }

}
