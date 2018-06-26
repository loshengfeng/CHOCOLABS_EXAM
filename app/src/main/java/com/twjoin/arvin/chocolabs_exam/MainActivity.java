package com.twjoin.arvin.chocolabs_exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twjoin.arvin.chocolabs_exam.api.ApiComponent;
import com.twjoin.arvin.chocolabs_exam.listener.HttpCallBack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ApiComponent apiComponent = ApiComponent.getInstance();
        apiComponent.getDrama(new HttpCallBack() {
            @Override
            public void onSuccess(Object obj) {

            }

            @Override
            public void onFail(String failReason) {

            }
        });
    }
}
