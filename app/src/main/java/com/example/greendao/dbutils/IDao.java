package com.example.greendao.dbutils;

import java.util.List;

/**
 * Created by lixingqu on 2017/1/3.
 */

public interface IDao<T> {
    boolean insert(T t);

    boolean delete(T t);

    boolean update(T t);

    List<T> queryAll();

    T queryById(long id);

    List<T> queryByObj(String where, String... params);
}
