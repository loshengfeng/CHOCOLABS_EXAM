package com.twjoin.arvin.chocolabs_exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.twjoin.arvin.chocolabs_exam.adapter.DramaAdapter;
import com.twjoin.arvin.chocolabs_exam.api.ApiComponent;
import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataListResponse;
import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataResponse;
import com.twjoin.arvin.chocolabs_exam.db.operation.DramaOperation;
import com.twjoin.arvin.chocolabs_exam.listener.HttpCallBack;
import com.twjoin.arvin.chocolabs_exam.listener.OnClickListener;
import com.twjoin.arvin.chocolabs_exam.model.Drama;
import com.twjoin.arvin.chocolabs_exam.utils.DimensionUtils;
import com.twjoin.arvin.chocolabs_exam.utils.GridSpacingItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String INTENT_DRAMA_DATA = "intent_drama_data";

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DramaAdapter adapter;
    private DramaOperation mDramaOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        callGetDramaApi();
    }

    private void initData() {
        ButterKnife.bind(this);
        mDramaOperation = DramaOperation.getInstance(this);
    }

    private void initView() {
        final DimensionUtils dimensionUtils = new DimensionUtils(this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        adapter = new DramaAdapter(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dimensionUtils.dpToPx(10)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Object obj) {
                final DramaDataResponse drama = (DramaDataResponse) obj;
                final Drama dramaModel = new Drama(drama);

                final Intent intent = new Intent(MainActivity.this, DramaInfoActivity.class);
                intent.putExtra(INTENT_DRAMA_DATA, dramaModel);
                startActivity(intent);
            }
        });
    }

    private void callGetDramaApi() {
        final ApiComponent apiComponent = ApiComponent.getInstance();
        apiComponent.getDrama(new HttpCallBack() {
            @Override
            public void onSuccess(Object obj) {
                final String data = (String) obj;
                final DramaDataListResponse dramaDataResponse = new Gson().fromJson(data, DramaDataListResponse.class);
                final List<DramaDataResponse> dramaDataList = dramaDataResponse.getDramaData();

                adapter.addAllData(dramaDataList);
                mDramaOperation.insertOrUpdate(dramaDataList);
            }

            @Override
            public void onFail(String failReason) {

            }
        });
    }
}
