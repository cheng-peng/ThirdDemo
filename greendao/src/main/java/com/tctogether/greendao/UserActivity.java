package com.tctogether.greendao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tctogether.greendao.model.User;
import com.tctogether.greendao.model.db.UserDB;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: UserActivity
 * 创 建 人: CXP
 * 创建日期: 2017-01-20 9:46
 * 描    述: 用户数据库管理
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class UserActivity extends AppCompatActivity {

    private String TAG = "CXP";

    private UserDB mUserDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mUserDB = UserDB.getInstance(getApplicationContext());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUserDB != null) {
            mUserDB = null;
        }
    }

    public void clickLis(View view) {
        switch (view.getId()) {
            case R.id.bt_user_add_one:
                //增加一条数据
                addOne();
                break;
            case R.id.bt_user_delete_one:
                //删除一条数据
                deleteOne();
                break;
            case R.id.bt_user_update_one:
                //修改一条数据
                updateOne();
                break;
            case R.id.bt_user_selece_one:
                //查询一条数据
                selectOne();
                break;
            case R.id.bt_user_add:
                //增加多条数据
                add();
                break;
            case R.id.bt_user_delete:
                //删除多条数据
                delete();
                break;
            case R.id.bt_user_update:
                //修改多条数据
                update();
                break;
            case R.id.bt_user_selece:
                //查询多条数据
                select();
                break;
        }
    }

    //增加一条数据
    private void addOne() {
        User user = new User();
        user.setName("CXP");
        user.setAge(18);
        mUserDB.insertUser(user);
    }

    //删除一条数据
    private void deleteOne() {
        mUserDB.deleteUser(mUserDB.queryUser("CXP1"));
    }

    //修改一条数据
    private void updateOne() {
        User user = mUserDB.queryUser("CXP");
        user.setName("CXP1");
        user.setAge(20);
        mUserDB.updateUser(user);
    }

    //查询一条数据
    private void selectOne() {
        User user = mUserDB.queryUser("CXP");
        Log.e(TAG, "name:" + user.getName() + "\nage:" + user.getAge());
    }

    //增加多条数据
    private void add() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("CXP" + (10 + i));
            user.setAge(10);
            list.add(user);
        }
        mUserDB.insertUserAll(list);
    }

    //删除多条数据
    private void delete() {
        mUserDB.deleteUserAll();
    }

    //修改多条数据
    private void update() {
        List<User> list = mUserDB.queryUser();
        for (User user : list) {
            user.setName("程小鹏");
        }
        mUserDB.updateUserAll(list);
    }

    //查询多条数据
    private void select() {
        List<User> list = mUserDB.queryUser();
        Log.e(TAG, "集合数:" + list.size());
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }
}
