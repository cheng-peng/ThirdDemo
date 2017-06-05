package com.cxp.lottie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 文 件 名: XMLActivity
 * 创 建 人: CXP
 * 创建日期: 2017-06-05 17:09
 * 描    述: XML设置动画
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class XMLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_xml.xml中 lottie_fileName="data.json"
        // 所以只需要在 app/src/main/assets 中添加AE 生成的 json文件，重命名为data.json就可以显示动画
        setContentView(R.layout.activity_xml);
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, XMLActivity.class);
        context.startActivity(intent);
    }
}
