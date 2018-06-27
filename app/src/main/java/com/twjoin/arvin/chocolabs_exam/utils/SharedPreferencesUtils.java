package com.twjoin.arvin.chocolabs_exam.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by arvin on 2018/6/27.
 */

@SuppressWarnings("DefaultFileTemplate")
public class SharedPreferencesUtils {

    private static final String SP_DRAMA = "sp_drama";
    private static final String SEARCH_TEXT = "search_text";

    private static SharedPreferencesUtils mSharedPreferencesUtils;

    private final SharedPreferences setting;
    private final SharedPreferences.Editor edit;

    @SuppressLint("CommitPrefEdits")
    private SharedPreferencesUtils(@NonNull Context context) {
        setting = context.getSharedPreferences(SP_DRAMA, Context.MODE_PRIVATE);
        edit = setting.edit();
    }

    public static SharedPreferencesUtils getInstance(@NonNull Context context) {
        if (null == mSharedPreferencesUtils) {
            mSharedPreferencesUtils = new SharedPreferencesUtils(context);
        }

        return mSharedPreferencesUtils;
    }

    public void setSearchText(String searchText) {
        edit.putString(SEARCH_TEXT, searchText).apply();
    }

    public String getSearchText() {
        return setting.getString(SEARCH_TEXT, "");
    }
}
