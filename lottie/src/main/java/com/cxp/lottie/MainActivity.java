package com.cxp.lottie;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
    }

    public void clickLis(View view) {
        switch (view.getId()) {
            case R.id.main_bt1:
                //XML设置动画
                XMLActivity.startActivity(mContext);
                break;
            case R.id.main_bt2:
                //代码设置动画
                CodeActivity.startActivity(mContext);
                break;
            case R.id.main_bt3:
                //网络设置动画
                NetworkActivity.startActivity(mContext);
                break;
        }
    }
}
