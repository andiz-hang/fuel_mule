package com.example.fuel_mule;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class CameraResultsFragment extends DisplayFragment {
    private static final @LayoutRes int ResID = R.layout.fragment_camera_results;

    private Button mSaveMealButton;
    private String mMealName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mSaveMealButton = v.findViewById(R.id.save_meal_button);
        mSaveMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.save_meal_dialog);

                final String mealName;
                EditText editText = dialog.findViewById(R.id.dialog_meal_name);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mMealName = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                Button cancel = dialog.findViewById(R.id.dialog_cancel_button);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button save = dialog.findViewById(R.id.dialog_save_meal_button);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QuickaddMeals qam = QuickaddMeals.get(getActivity());
                        qam.addMeal(new Meal(mMealName, Arrays.asList(600, 100, 3, 20, 5)));

                        new Toast(getActivity()).makeText(getActivity(), "Meal Saved", Toast.LENGTH_LONG).show();

                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        return v;
    }
}
