package com.twjoin.arvin.chocolabs_exam.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by arvin on 2018/6/27.
 */

@SuppressWarnings("DefaultFileTemplate")
public class StringUtils {

    private static final String TAG = StringUtils.class.getSimpleName();

    public static boolean isEmpty(String s) {
        return null == s || s.trim().equals("");
    }

    public static String getConvertTime(String utc) {
        final SimpleDateFormat parseDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        final SimpleDateFormat displayDateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        try {
            final Date date = parseDateFormat.parse(utc);
            return displayDateFormat.format(date);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

        return utc;
    }
}
