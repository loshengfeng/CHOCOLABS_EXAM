package com.twjoin.arvin.chocolabs_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.twjoin.arvin.chocolabs_exam.adapter.DramaAdapter;
import com.twjoin.arvin.chocolabs_exam.api.ApiComponent;
import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataListResponse;
import com.twjoin.arvin.chocolabs_exam.listener.HttpCallBack;
import com.twjoin.arvin.chocolabs_exam.utils.DimensionUtils;
import com.twjoin.arvin.chocolabs_exam.utils.GridSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DramaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        callGetDramaApi();
    }

    private void initView() {
        final DimensionUtils dimensionUtils = new DimensionUtils(this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        adapter = new DramaAdapter(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dimensionUtils.dpToPx(10)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void callGetDramaApi() {
        final ApiComponent apiComponent = ApiComponent.getInstance();
        apiComponent.getDrama(new HttpCallBack() {
            @Override
            public void onSuccess(Object obj) {
                final String data = (String) obj;
                final DramaDataListResponse dramaDataList = new Gson().fromJson(data, DramaDataListResponse.class);
                adapter.addAllData(dramaDataList.getDramaData());
            }

            @Override
            public void onFail(String failReason) {

            }
        });
    }
}
