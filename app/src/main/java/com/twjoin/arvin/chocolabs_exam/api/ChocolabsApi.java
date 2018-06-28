package com.twjoin.arvin.chocolabs_exam.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
interface ChocolabsApi {
    @GET("5a97c59c30000047005c1ed2")
    Observable<Response<ResponseBody>> getDramaResponse();
}
