package com.twjoin.arvin.chocolabs_exam.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.twjoin.arvin.chocolabs_exam.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class RetrofitComponent {

    private static Retrofit sRetrofit;

    public static Retrofit getInstance() {
        if (null == sRetrofit) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.CHOCOLABS_EXAM_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return sRetrofit;
    }
}
