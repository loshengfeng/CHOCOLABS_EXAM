package com.twjoin.arvin.chocolabs_exam.listener;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface HttpCallBack {
    @SuppressWarnings("EmptyMethod")
    void onSuccess(Object obj);
    @SuppressWarnings("EmptyMethod")
    void onFail(String failReason);
}
