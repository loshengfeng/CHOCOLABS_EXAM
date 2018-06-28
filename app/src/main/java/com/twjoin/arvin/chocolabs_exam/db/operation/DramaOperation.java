package com.twjoin.arvin.chocolabs_exam.db.operation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.twjoin.arvin.chocolabs_exam.MyApplication;
import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataResponse;
import com.twjoin.arvin.chocolabs_exam.db.DaoSession;
import com.twjoin.arvin.chocolabs_exam.db.DramaEntityDao;
import com.twjoin.arvin.chocolabs_exam.db.model.DramaEntity;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DramaOperation {

    private static DramaOperation instance;

    private final DaoSession mDaoSession;
    private final DramaEntityDao mDramaEntityDao;

    private DramaOperation(@NonNull Context context) {
        final Application application = ((Activity) context).getApplication();
        mDaoSession = ((MyApplication) application).getDaoSession();
        mDramaEntityDao = mDaoSession.getDramaEntityDao();
    }

    public static DramaOperation getInstance(@NonNull Context context) {
        if (null == instance) {
            instance = new DramaOperation(context);
        }
        return instance;
    }

    public synchronized void insertOrUpdate(List<DramaDataResponse> dramaDataList) {
        final List<DramaEntity> dramaEntityList = new ArrayList<>();

        for (DramaDataResponse drama : dramaDataList) {
            final DramaEntity dramaEntity = new DramaEntity(drama);
            dramaEntityList.add(dramaEntity);
        }

        mDramaEntityDao.insertOrReplaceInTx(dramaEntityList);
        mDaoSession.clear();
    }

    public List<DramaEntity> getDramaList() {
        return mDramaEntityDao.queryBuilder().list();
    }

    public synchronized List<DramaEntity> getDramaByQueryId(long dramaId) {
        final Query<DramaEntity> query = mDramaEntityDao.queryBuilder()
                .where(DramaEntityDao.Properties.DramaId.eq(dramaId)).build();

        mDaoSession.clear();
        return query.list();
    }
}
