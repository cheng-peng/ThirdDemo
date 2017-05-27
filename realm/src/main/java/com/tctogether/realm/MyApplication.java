package com.tctogether.realm;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * 文 件 名: MyApplication
 * 创 建 人: CXP
 * 创建日期: 2017-01-13 10:52
 * 描    述: 初始化
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        //默认配置
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        /**
         * 自定义配置
         * name 配置名字
         * encryptionKey 加密用字段,不是64位会报错
         * schemaVersion 版本号
         * 不同的activity使用同一个.realm文件时配置必须相同；
         * 所有操作都必须在事务内执行；
         */
//        RealmConfiguration configuration = new RealmConfiguration.Builder()
//                .name("cxp")//配置文件名
//                .schemaVersion(1) //数据库版本
////                .encryptionKey(new byte[64]) //用来加密数据库
//                //.inMemory() //在内存中创建realm来使用，通用配置不需要该项
//                .build();
        Realm.setDefaultConfiguration(configuration);

        //Google调试
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
//                                .databaseNamePattern(Pattern.compile("cxp"))
                                .build())
                        .build());

    }
}
