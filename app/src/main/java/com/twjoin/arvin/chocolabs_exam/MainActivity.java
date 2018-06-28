package com.twjoin.arvin.chocolabs_exam;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.gson.Gson;
import com.twjoin.arvin.chocolabs_exam.adapter.DramaAdapter;
import com.twjoin.arvin.chocolabs_exam.api.ApiComponent;
import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataListResponse;
import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataResponse;
import com.twjoin.arvin.chocolabs_exam.db.model.DramaEntity;
import com.twjoin.arvin.chocolabs_exam.db.operation.DramaOperation;
import com.twjoin.arvin.chocolabs_exam.listener.HttpCallBack;
import com.twjoin.arvin.chocolabs_exam.listener.OnClickListener;
import com.twjoin.arvin.chocolabs_exam.model.Drama;
import com.twjoin.arvin.chocolabs_exam.utils.DimensionUtils;
import com.twjoin.arvin.chocolabs_exam.utils.GridSpacingItemDecoration;
import com.twjoin.arvin.chocolabs_exam.utils.SharedPreferencesUtils;
import com.twjoin.arvin.chocolabs_exam.utils.StringUtils;

import java.util.ArrayList;
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

    private SharedPreferencesUtils sharedPreferencesUtils;

    private String searchText;

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
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance(getApplicationContext());
        searchText = sharedPreferencesUtils.getSearchText();
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
                final DramaEntity dramaEntity = (DramaEntity) obj;
                final Drama dramaModel = new Drama(dramaEntity);

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

                mDramaOperation.insertOrUpdate(dramaDataList);
                loadDramaFromDbAndDisplay();
            }

            @Override
            public void onFail(String failReason) {
                loadDramaFromDbAndDisplay();
            }
        });
    }

    private synchronized void loadDramaFromDbAndDisplay() {
        filterDramaAndDisplay(searchText);
    }

    private synchronized void filterDramaAndDisplay(String searchText) {
        sharedPreferencesUtils.setSearchText(searchText);

        final List<DramaEntity> dramaEntityList = mDramaOperation.getDramaList();

        if (StringUtils.isEmpty(searchText)) {
            adapter.addAllData(dramaEntityList);
        } else {
            final List<DramaEntity> dramaFilterList = new ArrayList<>();
            for (DramaEntity dramaEntity : dramaEntityList) {
                if (dramaEntity.getDramaName().contains(searchText)) {
                    dramaFilterList.add(dramaEntity);
                }
            }
            adapter.addAllData(dramaFilterList);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (null == searchManager) {
            return true;
        }

        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setQuery(searchText, false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterDramaAndDisplay(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDramaAndDisplay(newText);
                return false;
            }
        });

        return true;
    }
}