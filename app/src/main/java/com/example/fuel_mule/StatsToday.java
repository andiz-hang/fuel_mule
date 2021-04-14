package com.example.fuel_mule;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsToday {

    private static com.example.fuel_mule.StatsToday sStatsToday;

    private Meal mStats;
    private List<Integer> mLimits; // 2000, 5000, 30, 30, 30
    private List<Integer> mPercentages;

    public static com.example.fuel_mule.StatsToday get(Context context) {
        if (sStatsToday == null) {
            sStatsToday = new com.example.fuel_mule.StatsToday(context);
        }
        return sStatsToday;
    }

    private StatsToday (Context context) {
        mLimits = Arrays.asList(2000, 500, 30, 30, 30);
        resetStats();

//        checkIfOverLimit();
    }

//    private void checkIfOverLimit() {
//
//        for (int i = 0; i < 5; i++) {
//            if (mStats.getStats().get(i) > mLimits.get(i)) {
//                mStats.setStat(i, mLimits.get(i));
//            }
//        }
//    }

    private void calculatePercentages() {

        List<Integer> out = Arrays.asList(0,0,0,0,0);
        List<Integer> stats = mStats.getStats();

        for (int i = 0; i < 5; i++) {
            int value = stats.get(i);
            int limit = mLimits.get(i);

            if (value >= limit) out.set(i, 100);
            else {
                int res = value * 100 / limit;
                out.set(i, res);
            }
        }

        mPercentages = out;
    }

    public Meal getStats() {
        return mStats;
    }

    public List<Integer> getPercentages() { return mPercentages; }

    public void setLimits(List<Integer> l) {
        mLimits = l;
    }

    public List<Integer> getLimits() {
        return mLimits;
    }

    public void addStats(Meal m) {
        mStats.add(m);
        calculatePercentages();
    }

    public void resetStats() {
        mStats = new Meal("Stats", Arrays.asList(0, 0, 0, 0, 0));
        calculatePercentages();
    }
}
