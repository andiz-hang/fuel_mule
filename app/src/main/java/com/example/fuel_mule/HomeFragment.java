package com.example.fuel_mule;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends DisplayFragment {
    private static final @LayoutRes int ResID = R.layout.fragment_home;

    private List<ProgressBar> mPBars;
    private static StatsToday mST;

    // ------------------------  QUICKADD ------------------------


    private RecyclerView mQuickAddMenu;
    private MealAdapter mMealAdapter;

//    private class Quickadd

    private class MealHolder extends RecyclerView.ViewHolder {
        private final TextView mNameTextView;
//        private ImageButton mDeleteButton;

        public MealHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.grid_item_quickadd_meal, parent, false));

            mNameTextView = itemView.findViewById(R.id.meal_name);
//            mDeleteButton = itemView.findViewById(R.id.delete_button);
        }

        public void bind(final Meal meal) {
            mNameTextView.setText(meal.getName());
            mNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mST.addStats(meal);
                    updatePBars();
                }
            });
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

        mST = StatsToday.get(getActivity());

        initiatePBars(v);

        mQuickAddMenu = v.findViewById(R.id.quick_add);
        mQuickAddMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        updateQuickaddMenu();

        return v;
    }

    private void initiatePBars(View v) {
        ProgressBar mCalBar = v.findViewById(R.id.cal_bar);
        ProgressBar mSodBar = v.findViewById(R.id.sod_bar);
        ProgressBar mFatBar = v.findViewById(R.id.fat_bar);
        ProgressBar mCarbsBar = v.findViewById(R.id.carbs_bar);
        ProgressBar mProBar = v.findViewById(R.id.pro_bar);

        mPBars = Arrays.asList(mCalBar, mSodBar, mFatBar, mCarbsBar, mProBar);

        updatePBars();
    }

    @SuppressLint("NewApi")
    private void updatePBars() {

        for (int i = 0; i < 5; i++) {
            int progress = mST.getPercentages().get(i);
            mPBars.get(i).setProgress(progress, true);
        }
    }

    private void updateQuickaddMenu() {
        QuickaddMeals qam = QuickaddMeals.get(getActivity());
        List<Meal> meals = qam.getMeals();

        mMealAdapter = new MealAdapter(meals);
        mQuickAddMenu.setAdapter(mMealAdapter);
    }

}
