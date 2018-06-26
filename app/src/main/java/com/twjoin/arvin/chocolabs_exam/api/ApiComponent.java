package com.twjoin.arvin.chocolabs_exam.api;

import android.support.annotation.NonNull;

import com.twjoin.arvin.chocolabs_exam.listener.HttpCallBack;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class ApiComponent {
    private static ApiComponent sApiComponent;
    private final ChocolabsApi mChocolabsApi;

    private ApiComponent() {
        mChocolabsApi = RetrofitComponent.getInstance().create(ChocolabsApi.class);
    }

    public static ApiComponent getInstance() {
        if (null == sApiComponent) {
            sApiComponent = new ApiComponent();
        }

        return sApiComponent;
    }

    public synchronized void getDrama(@NonNull final HttpCallBack httpCallBack) {
        final Observable<Response<ResponseBody>> observable = mChocolabsApi.getDramaResponse();
        callApi(observable, httpCallBack);
    }

    private synchronized void callApi(@NonNull Observable<Response<ResponseBody>> observable, @NonNull HttpCallBack httpCallBack) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver(httpCallBack));
    }

    class CustomObserver extends DefaultObserver<Response<ResponseBody>> {

        private final HttpCallBack mHttpCallBack;

        public CustomObserver(@NonNull HttpCallBack httpCallBack) {
            mHttpCallBack = httpCallBack;
        }

        @Override
        public void onNext(Response<ResponseBody> responseBodyResponse) {

            if (responseBodyResponse.isSuccessful()) {
                final ResponseBody responseBody = responseBodyResponse.body();

                if (null != responseBody) {
                    try {
                        mHttpCallBack.onSuccess(responseBody.string());
                    } catch (Exception e) {
                        mHttpCallBack.onFail("IO Exception");
                    }
                } else {
                    mHttpCallBack.onFail("Response body is null");
                }
            } else {
                mHttpCallBack.onFail("Fail Response");
            }

        }

        @Override
        public void onError(Throwable e) {
            mHttpCallBack.onFail(e.toString());
        }

        @Override
        public void onComplete() {

        }
    }
}
