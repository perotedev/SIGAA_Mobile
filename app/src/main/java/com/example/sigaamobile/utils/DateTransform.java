package com.example.sigaamobile.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform {
    public static final int SECONDS = 1;
    public static final int MILISSECONDS = 2;

    public static String transformToStringDate(int unixTimestamp, int type){
        long timestamp;

        if (type == DateTransform.SECONDS){
            timestamp = unixTimestamp*1000L;
        } else if (type == DateTransform.MILISSECONDS){
            timestamp = unixTimestamp;
        } else {
            throw new RuntimeException("Type not accepted!");
        }

        Date date = new java.util.Date(timestamp);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }
}
