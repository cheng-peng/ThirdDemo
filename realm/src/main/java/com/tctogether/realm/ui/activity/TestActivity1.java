package com.tctogether.realm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tctogether.realm.R;
import com.tctogether.realm.module.User;
import com.tctogether.realm.utils.CommonUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.tctogether.realm.R.id.delete;
import static com.tctogether.realm.R.id.update;

/**
 * 文 件 名: TestActivity1
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 11:19
 * 描    述: 同步操作
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TestActivity1 extends AppCompatActivity {

    private Button btAdd;
    private Button btDelete;
    private Button btUpdate;
    private Button btQuery;
    private Button btQueryDuo;
    private Realm mRealm;
    private RealmResults<User> users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        btAdd = (Button) findViewById(R.id.add);
        btDelete = (Button) findViewById(R.id.delete);
        btUpdate = (Button) findViewById(R.id.update);
        btQuery = (Button) findViewById(R.id.query);
        btQueryDuo = (Button) findViewById(R.id.query_duo);

        btAdd.setOnClickListener(clickLis);
        btDelete.setOnClickListener(clickLis);
        btUpdate.setOnClickListener(clickLis);
        btQuery.setOnClickListener(clickLis);
        btQueryDuo.setOnClickListener(clickLis);

        mRealm = Realm.getDefaultInstance();
        users = mRealm.where(User.class).findAll();
        //数据库改变回调
        users.addChangeListener(callback);
    }

    //单击事件
    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.add:
                    //新增数据
                    add();
                    break;
                case delete:
                    //删除数据
                    delete();
                    break;
                case update:
                    //更新数据
                    update();
                    break;
                case R.id.query:
                    //条件查询
                    query();
                    break;
                case R.id.query_duo:
                    //多表查询
                    queryDuo();
                    break;
            }
        }
    };

    //新增数据
    private void add() {
        //第一种
        //开启事务
        mRealm.beginTransaction();
        User user = mRealm.createObject(User.class, CommonUtils.getUUID());
        user.setName("CXP");
        user.setAge(22);
        //提交事务
        mRealm.commitTransaction();

        //第二种
//        User user = new User();
//        user.setId(CommonUtils.getUUID());
//        user.setName("程小鹏1。");
//        user.setAge(10);
//        RealmList<Student> students = new RealmList<>();
//        Student s = new Student();
//        s.setName("程小鹏1");
//        s.setCity("上海1");
//        s.setId(CommonUtils.getUUID());
//        students.add(s);
//        for (int i = 5; i < 10; i++) {
//            Student student = new Student();
//            student.setName("程小鹏" + i);
//            student.setCity("上海" + i);
//            student.setId(CommonUtils.getUUID());
//            students.add(student);
//        }
//        user.setStudents(students);
//
//        //开启事务
//        mRealm.beginTransaction();
//        //对象转Realm
//        User realmUser = mRealm.copyToRealm(user);
//        //提交事务
//        mRealm.commitTransaction();

    }

    //删除数据
    private void delete() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                //根据下标删除数据
//                User user = users.get(2);
//                user.deleteFromRealm();
                //删除第一个数据
//                users.deleteFirstFromRealm();
                //删除最后一个数据
//                users.deleteLastFromRealm();
                //删除下标为1的数据
//                users.deleteFromRealm(1);
                //删除所有数据
                users.deleteAllFromRealm();
            }
        });

    }


    //更新数据
    private void update(){
        User user = mRealm.where(User.class).equalTo("name", "CXP").findFirst();
        mRealm.beginTransaction();
        user.setName("程小鹏啊");
        mRealm.commitTransaction();
    }

    //根据条件查询
    private void query() {
        RealmResults<User> users =
                mRealm.where(User.class)
                        //等于关系
//                        .equalTo("name", "程小鹏。")
                        //或者的关系
//                        .or()
//                        .equalTo("name", "程小鹏1。")
                        //以C开头名字
//                        .beginsWith("name","C")
                        //以P结尾名字
//                        .endsWith("name","P")
                        //年龄大于10的
//                        .greaterThan("age",10)
                        //年龄大于等于10的
//                        .greaterThanOrEqualTo("age",10)
                        //年龄小于10的
//                        .lessThan("age",10)
                        //年龄小于等于10的
//                        .lessThanOrEqualTo("age",10)
                        //不等于
//                        .notEqualTo("name","CXP")
                        //年龄在10到22之间 包含这两个数
//                        .between("age", 10, 22)
                        //查询所有
                        .findAll();
        //倒序
//        RealmResults<User> users1= users.sort("age", Sort.DESCENDING);
        //正序
//        RealmResults<User> users1= users.sort("age", Sort.ASCENDING);
        //排重
//        RealmResults<User> users1= users.distinct("name");
        //求和
//        long sum = users.sum("age").longValue();
        //最小值
//        long min = users.min("age").longValue();
        //最大值
//        long max = users.max("age").longValue();
        //平均值
//        double average = users.average("age");
        //集合个数
//        long matches = users.size();
        List<User> listUser = mRealm.copyFromRealm(users);
        Log.e("CXP", "" + listUser.size());
    }

    //多表查询
    private void queryDuo() {
        //条件是and
        RealmResults<User> users1 =
                mRealm.where(User.class)
                        .equalTo("students.name", "程小鹏1")
                        .equalTo("students.city", "上海1")
                        .findAll();

        //.findAll()后面跟.where()意思是根据上面条件查出的数据再继续查
        RealmResults<User> users2 =
                mRealm.where(User.class)
                        .equalTo("students.name", "程小鹏1")
                        .findAll()
                        .where()
                        .equalTo("students.city", "上海1")
                        .findAll()
                        .where()
                        .equalTo("students.name", "程小鹏2")
                        .findAll();
        //Realm转对象
        List<User> listUser1 = mRealm.copyFromRealm(users1);
        List<User> listUser2 = mRealm.copyFromRealm(users2);
        Log.e("CXP1", "" + listUser1.size());
        Log.e("CXP2", "" + listUser2.size());

    }

    //数据库改变回调
    private RealmChangeListener callback = new RealmChangeListener<RealmResults<User>>() {
        @Override
        public void onChange(RealmResults<User> results) {
            Log.e("CXP1", "" + results.size());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除回调
        users.removeChangeListeners();
        //关闭Realm
        mRealm.close();
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestActivity1.class);
        context.startActivity(intent);
    }
}
