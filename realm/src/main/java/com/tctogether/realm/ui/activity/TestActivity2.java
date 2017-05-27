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

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * 文 件 名: TestActivity1
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 11:19
 * 描    述: 异步操作
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TestActivity2 extends AppCompatActivity {

    private Button btAsyncAdd;
    private Button btAsyncDelete;
    private Button btAsyncUpdate;
    private Button btAsyncQuery;
    private Realm mRealm;
    private RealmAsyncTask addTask;
    private RealmResults<User> users;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        btAsyncAdd = (Button) findViewById(R.id.async_add);
        btAsyncDelete = (Button) findViewById(R.id.async_delete);
        btAsyncUpdate = (Button) findViewById(R.id.async_update);
        btAsyncQuery = (Button) findViewById(R.id.async_query);

        btAsyncAdd.setOnClickListener(clickLis);
        btAsyncDelete.setOnClickListener(clickLis);
        btAsyncUpdate.setOnClickListener(clickLis);
        btAsyncQuery.setOnClickListener(clickLis);

        mRealm = Realm.getDefaultInstance();
        users= mRealm.where(User.class).findAll();
    }

    //单击事件
    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.async_add:
                    User user = new User();
                    user.setId(CommonUtils.getUUID());
                    user.setName("mm");
                    user.setAge(16);
                    //异步添加数据
                    asyncAdd(user);
                    break;
                case R.id.async_delete:
                    //异步删除数据
                    asyncDelete("mm");
                    break;
                case R.id.async_update:
                    //异步修改数据
                    asyncUpdate("mm");
                    break;
                case R.id.async_query:
                    //异步查询  先点这个按钮，在去执行数据库的改变（增删改），然后看Log
                    users.addChangeListener(callback);
                    break;
            }
        }
    };

    //异步添加数据
    private void asyncAdd(final User user) {
        addTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(user);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("CXP", "添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("CXP", "添加失败");
            }
        });
    }

    //异步删除数据
    private void asyncDelete(final String name){
        addTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user=realm.where(User.class).equalTo("name",name).findFirst();
                user.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("CXP", "删除成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("CXP", "删除失败");
            }
        });
    }

    //异步修改数据
    private void asyncUpdate(final String name){
        addTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user=realm.where(User.class).equalTo("name",name).findFirst();
                user.setName("gg");
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("CXP", "更新成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("CXP", "更新失败");
            }
        });
    }

    //异步查询
    private RealmChangeListener callback = new RealmChangeListener<RealmResults<User>>() {
        @Override
        public void onChange(RealmResults<User> results) {
            Log.e("CXP", "异步查询数据：" + results.size());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消监听
        if (addTask!=null&&!addTask.isCancelled()){
            addTask.cancel();
        }
        //移除回调
        users.removeChangeListeners();
        //关闭Realm
        mRealm.close();
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestActivity2.class);
        context.startActivity(intent);
    }
}
