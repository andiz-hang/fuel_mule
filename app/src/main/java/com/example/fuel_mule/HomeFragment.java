package com.example.fuel_mule;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends DisplayFragment {
    private static final @LayoutRes int ResID = R.layout.fragment_home;

    private RecyclerView mQuickAddMenu;
    private MealAdapter mAdapter;

//    private class Quickadd

    private static class MealHolder extends RecyclerView.ViewHolder {
        private final TextView mNameTextView;
        private ImageButton mDeleteButton;

        public MealHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.grid_item_quickadd_meal, parent, false));

            mNameTextView = itemView.findViewById(R.id.meal_name);
            mDeleteButton = itemView.findViewById(R.id.delete_button);
        }

        public void bind(Meal meal) {
            mNameTextView.setText(meal.getName());
        }
    }

    private class MealAdapter extends RecyclerView.Adapter<HomeFragment.MealHolder> {
        private final List<Meal> mMeals;

        public MealAdapter(List<Meal> m) {
            mMeals = m;
        }

        @NonNull @Override
        public HomeFragment.MealHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new HomeFragment.MealHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeFragment.MealHolder mh, int i) {
            Meal m = mMeals.get(i);
            mh.bind(m);
        }

        @Override
        public int getItemCount() {
            return mMeals.size();
        }

        public List<Meal> getList() {
            return mMeals;
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mQuickAddMenu = v.findViewById(R.id.quick_add);
        mQuickAddMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        updateQuickaddMenu();

        return v;
    }

    private void updateQuickaddMenu() {
        QuickaddMeals qam = QuickaddMeals.get(getActivity());
        List<Meal> meals = qam.getMeals();

        mAdapter = new MealAdapter(meals);
        mQuickAddMenu.setAdapter(mAdapter);
    }

}
