package com.cxp.zip;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.zip.ZipException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLis(View view) {
        switch (view.getId()) {
            case R.id.main_zip:
                //测试数据，注意更换目录
                LinkedList<File> files = DirTraversal.listLinkedFiles(Environment.getExternalStorageDirectory()+"/111");

                File fl = DirTraversal.getFilePath(Environment.getExternalStorageDirectory()+"/", "111.zip");

                try {
                    if (ZipUtils.zipFiles(files, fl)) {
                        Toast.makeText(MainActivity.this, "压缩完成", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //第二种实现
                //ZipUtils.zip(pathString+"/Android/data/com.mapbar.info.collection/files/cache", pathString+"/Android/data/com.mapbar.info.collection/files/cache.zip");
                //ZipUtils.unzip(pathString+"/Android/data/com.mapbar.info.collection/files/cache.zip", pathString+"/Android/data/com.mapbar.info.collection/files/cache");
                break;
            case R.id.main_unzip:
                File file = DirTraversal.getFilePath(Environment.getExternalStorageDirectory() + "/", "Disney.zip");
                try {
                    if (ZipUtils.upZipFile(file, Environment.getExternalStorageDirectory() + "/111/")) {
                        Toast.makeText(MainActivity.this, "解压完成", Toast.LENGTH_SHORT).show();
                    }
                } catch (ZipException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
