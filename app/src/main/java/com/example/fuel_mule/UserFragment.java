package com.example.fuel_mule;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"ConstantConditions"})
public class UserFragment extends DisplayFragment {

    private static final @LayoutRes int ResID = R.layout.fragment_user;

    private final String mSentenceStart = "Current Subscription Level: ";
    private final List<String> mSubLevels = Arrays.asList("Regular", "Professional", "Corporate");

    private int mSubLevel;
    private TextView mSubTextView;

    private Button mChangeSubLevelButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mSubLevel = 0;
        mSubTextView = v.findViewById(R.id.subscription_level);

        mChangeSubLevelButton = v.findViewById(R.id.change_sub_level_button);
        mChangeSubLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.change_sub_level_dialog);

                Spinner subSelection = dialog.findViewById(R.id.sub_selection);
                String[] items = getSubSelectItems();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
                subSelection.setAdapter(adapter);

                subSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mSubLevel = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Button okay = dialog.findViewById(R.id.dialog_okay_button);
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String out = mSentenceStart + mSubLevels.get(mSubLevel);
                        mSubTextView.setText(out);
                        dialog.dismiss();
                    }
                });

                Button cancel = dialog.findViewById(R.id.dialog_cancel_button);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return v;
    }

    private String[] getSubSelectItems() {
        return mSubLevels.toArray(new String[0]);
    }
}
