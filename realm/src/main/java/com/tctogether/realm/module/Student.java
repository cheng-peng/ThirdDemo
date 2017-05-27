package com.tctogether.realm.module;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 文 件 名: Student
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 16:34
 * 描    述: 实体类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class Student extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
