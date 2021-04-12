package com.example.fuel_mule;

import java.util.Arrays;
import java.util.List;

public class Meal {
    private final String mName;

    private int mCalories;
    private int mSodium;
    private int mFat;
    private int mCarbs;
    private int mProtein;

    public Meal(String name) {
        mName = name;

        mCalories = 0;
        mSodium = 0;
        mFat = 0;
        mCarbs = 0;
        mProtein = 0;
    }

    public Meal(String name, List<Integer> nutrition) {
        mName = name;

        mCalories = nutrition.get(0);
        mSodium = nutrition.get(1);
        mFat = nutrition.get(2);
        mCarbs = nutrition.get(3);
        mProtein = nutrition.get(4);
    }

    public void add(Meal m) {
        mCalories += m.mCalories;
        mSodium += m.mSodium;
        mFat += m.mFat;
        mCarbs += m.mCarbs;
        mProtein += m.mProtein;
    }

    public String getName() {
        return mName;
    }

    public List<Integer> getStats() {
        return Arrays.asList(mCalories, mSodium, mFat, mCarbs, mProtein);
    }

    public void setStat(int i, int value) {
        if (i >= 5) {
            return;
        }

        switch (i) {
            case 0:
                mCalories = value;
                break;
            case 1:
                mSodium = value;
                break;
            case 2:
                mFat = value;
                break;
            case 3:
                mCarbs = value;
                break;
            case 4:
                mProtein = value;
                break;
        }
    }
}
