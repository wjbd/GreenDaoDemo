package com.example.greendao.dbutils;

import android.content.Context;

import com.greendao.DaoMaster;
import com.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lixingqu on 2017/1/3.
 */

public class DaoManager {
    private static String DB_NAME = "test.db";
    private static DaoManager mDaoManager;
    private static MySqlLiteOpenHelper mySqlLiteOpenHelper;
    private static DaoSession mDaoSession;
    private static Database mDatabase;
    private DaoManager() {}

    public static DaoManager getInstance(){
        if (mDaoManager == null){
            synchronized (DaoManager.class){
                if (mDaoManager == null){
                    mDaoManager = new DaoManager();
                }
            }
        }
        return mDaoManager;
    }

    public static void init(Context context){
        mySqlLiteOpenHelper = new MySqlLiteOpenHelper(context,DB_NAME,null);
        mDatabase = mySqlLiteOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(mDatabase).newSession();
    }

    public  DaoSession getDaoSession(){
        return mDaoSession;
    }
}
