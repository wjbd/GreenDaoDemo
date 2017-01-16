package com.example.greendao.db;

import android.util.Log;

import com.example.greendao.dbutils.DaoManager;
import com.example.greendao.dbutils.IDao;
import com.example.greendao.entity.User;
import com.greendao.DaoSession;
import com.greendao.UserDao;

import java.util.List;

/**
 * Created by lixingqu on 2017/1/3.
 */

public class UserDaoManagr implements IDao<User> {
    private DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();

    @Override
    public boolean insert(User user) {
        return mDaoSession.getUserDao()
                .insert(user) > 0 ? true : false;
    }

    @Override
    public boolean delete(User user) {
        try {
            mDaoSession.getUserDao()
                    .delete(user);
        } catch (Exception e) {
            Log.e("lxq", "删除失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            mDaoSession.getUserDao()
                    .update(user);
        } catch (Exception e) {
            Log.e("lxq", "更新失败");
            return false;
        }
        return true;
    }

    @Override
    public List<User> queryAll() {
        return mDaoSession.getUserDao()
                .loadAll();
    }

    @Override
    public User queryById(long id) {
        return mDaoSession.getUserDao()
                .loadByRowId(id);
    }

    @Override
    public List<User> queryByObj(String where, String... params) {
        return mDaoSession.getUserDao()
                .queryRaw(where, params);
    }

    public User queryByName(String name) {
        return mDaoSession.getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.Name.eq(name))
                .build()
                .unique();
    }


}
