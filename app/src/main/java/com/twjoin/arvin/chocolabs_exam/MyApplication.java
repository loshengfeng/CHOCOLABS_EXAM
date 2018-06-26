package com.twjoin.arvin.chocolabs_exam;

import android.app.Application;

import com.twjoin.arvin.chocolabs_exam.db.DaoMaster;
import com.twjoin.arvin.chocolabs_exam.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class MyApplication extends Application {

    private static final String DB_NAME = "exam.db";

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        final DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        final Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
