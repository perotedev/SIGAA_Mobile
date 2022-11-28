package com.example.sigaamobile.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform {
    public static final int SECONDS = 1;
    public static final int MILISSECONDS = 2;

    public static String transformToStringDate(long unixTimestamp, int type){
        long timestamp = unixTimestamp;

        if (type == SECONDS){
            timestamp = timestamp*1000L;
        }

        if (type != MILISSECONDS && type != SECONDS || timestamp < 0){
            throw new RuntimeException("Timestamp or type invalid!");
        }

        Date date = new java.util.Date(timestamp);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }
}
