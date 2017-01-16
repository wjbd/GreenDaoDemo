package com.example.greendao;

import android.app.Application;

import com.example.greendao.dbutils.DaoManager;

/**
 * Created by lixingqu on 2017/1/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.init(this);
    }
}
