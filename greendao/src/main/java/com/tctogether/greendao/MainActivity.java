package com.tctogether.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLis(View view) {
        switch (view.getId()) {
            case R.id.bt_user:
                UserActivity.startActivity(MainActivity.this);
                break;
        }
    }
}