package com.tctogether.realm.module;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 文 件 名: User
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 11:08
 * 描    述: 实体类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class User extends RealmObject {

    /**
     * 1、支持的数据类型：boolean, byte, short, int, long, float, double, String, Date and byte[],在Realm中byte, short, int, long最终都被映射成long类型
     * 2、注解说明:@PrimaryKey:字段必须是String、 integer、byte、short、 int、long 以及它们的封装类Byte, Short, Integer, and Long;
     *                         使用了该注解之后可以使用copyToRealmOrUpdate()方法，通过主键查询它的对象，如果查询到了，则更新它，否则新建一个对象来代替;
     *                         使用了该注解将默认设置（@index）注解
     *                         使用了该注解之后，创建和更新数据将会慢一点，查询数据会快一点。
     * 2、注解说明:@Required： 数据不能为null。
     * 2、注解说明:@Ignore：   忽略，即该字段不被存储到本地。
     * 2、注解说明:@Index：    为这个字段添加一个搜索引擎，这将使插入数据变慢、数据增大，但是查询会变快。建议在需要优化读取性能的情况下使用。
     *
     *
     */

    @PrimaryKey
    private String id;

    private String name;
    private int age;
    private RealmList<Student> students;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RealmList<Student> getStudents() {
        return students;
    }

    public void setStudents(RealmList<Student> students) {
        this.students = students;
    }
}
