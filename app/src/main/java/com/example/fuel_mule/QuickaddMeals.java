package com.example.fuel_mule;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuickaddMeals {
    private static QuickaddMeals sQuickaddMeals;

    private List<Meal> mMeals;

    public static QuickaddMeals get(Context context) {
        if (sQuickaddMeals == null) {
            sQuickaddMeals = new QuickaddMeals(context);
        }
        return sQuickaddMeals;
    }

    private QuickaddMeals (Context context) {
        mMeals = new ArrayList<>();

        // TODO: Testing
        mMeals.add(new Meal("The Best Meal"));
    }

    public List<Meal> getMeals() {
        return mMeals;
    }

    public Meal getMeal(String name) {
        for (Meal meal : mMeals) {
            if (meal.getName().equals(name)) {
                return meal;
            }
        }
        return null;
    }

    public void addMeal(Meal m) {
        mMeals.add(m);
    }

}
