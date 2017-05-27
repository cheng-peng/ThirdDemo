package com.tctogether.realm.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tctogether.realm.R;

/**
 * Realm数据库
 * 打印的都是日志
 */
public class MainActivity extends AppCompatActivity {

    private Button bt;
    private Button btAsync;
    private Button btJson;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        bt = (Button) findViewById(R.id.bt);
        btAsync = (Button) findViewById(R.id.async_bt);
        btJson = (Button) findViewById(R.id.bt_json);


        bt.setOnClickListener(clickLis);
        btAsync.setOnClickListener(clickLis);
        btJson.setOnClickListener(clickLis);

    }

    //单击事件
    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt:
                    TestActivity1.startActivity(mContext);
                    break;
                case R.id.async_bt:
                    TestActivity2.startActivity(mContext);
                    break;
                case R.id.bt_json:
                    TestActivity3.startActivity(mContext);
                    break;
            }
        }
    };


}