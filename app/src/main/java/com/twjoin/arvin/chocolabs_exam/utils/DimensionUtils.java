package com.twjoin.arvin.chocolabs_exam.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DimensionUtils {
    private final Context mContext;

    public DimensionUtils(@NonNull Context context) {
        mContext = context;
    }

    public int dpToPx(@SuppressWarnings("SameParameterValue") int dp) {
        final Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
