package com.example.scorecard.utilities;

import com.example.scorecard.CommonConstants;

public class CommonUtility {

    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
