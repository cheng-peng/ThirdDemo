package com.cxp.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RangeView rv;

    @Override//进行活动创建的初始化操作
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置显示内容的视图
        setContentView(R.layout.test);
        rv = (RangeView) findViewById(R.id.test);
        rv.setOnSeekChangeListener(new RangeView.OnSeekChangeListener() {
            @Override
            public void onChanged(int leftValue, int rightValue) {
                Toast.makeText(MainActivity.this, leftValue + "______" + rightValue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
