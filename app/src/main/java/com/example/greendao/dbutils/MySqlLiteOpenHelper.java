package com.example.greendao.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.greendao.DaoMaster;
import com.greendao.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lixingqu on 2017/1/3.
 */

public class MySqlLiteOpenHelper extends DaoMaster.OpenHelper{

    public MySqlLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("lxq","oldVersion:"+oldVersion+",newVersion"+newVersion);
        MigrationHelper.getInstance().migrate(db,UserDao.class);
    }

}
