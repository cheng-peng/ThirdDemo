package com.tctogether.realm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tctogether.realm.R;
import com.tctogether.realm.module.Student;
import com.tctogether.realm.module.User;
import com.tctogether.realm.utils.CommonUtils;
import com.google.gson.Gson;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * 文 件 名: TestActivity1
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 11:19
 * 描    述: Json操作
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TestActivity3 extends AppCompatActivity {

    private Button btGsonRealm;
    private Button btJson;
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        mRealm = Realm.getDefaultInstance();

        btGsonRealm = (Button) findViewById(R.id.gson_realm);
        btJson = (Button) findViewById(R.id.json);

        btGsonRealm.setOnClickListener(clickLis);
        btJson.setOnClickListener(clickLis);
    }

    //单击事件
    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.gson_realm:
                    //gson和realm使用
                    gsonRealm();
                    break;
                case R.id.json:
                    //json直接插入
                    json();
                    break;
            }
        }
    };

    //gson和realm使用
    private void gsonRealm() {
        Gson gson = new Gson();
//        String json = " {\"id\":\"1\",\"name\":\"gg\",\"age\":8}";
//        User user = gson.fromJson(json, User.class);
//        //开启事务
//        mRealm.beginTransaction();
//        User realmUser = mRealm.copyToRealm(user);
//        //提交事务
//        mRealm.commitTransaction();
        User user = new User();
        user.setId(CommonUtils.getUUID());
        user.setName("YY");
        user.setAge(100);
        RealmList<Student> students = new RealmList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName("CXP" + i);
            student.setId("" + i);
            student.setCity("北京" + i);
            students.add(student);
        }
        user.setStudents(students);
        String json = gson.toJson(user);
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(User.class, json);
        mRealm.commitTransaction();
    }

    //json直接使用
    private void json() {
        String json = " {\"id\":\"2\",\"name\":\"mm\",\"age\":18}";
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(User.class, json);
        mRealm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestActivity3.class);
        context.startActivity(intent);
    }
}
