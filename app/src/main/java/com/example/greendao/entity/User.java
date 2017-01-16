package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xxx on 2016/11/7.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    @NotNull
    private String name;
    private int age;
    private int cdname;

    @Generated(hash = 2124964736)
    public User(Long id, @NotNull String name, int age, int cdname) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cdname = cdname;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCdname() {
        return this.cdname;
    }

    public void setCdname(int cdname) {
        this.cdname = cdname;
    }


}
