package com.tctogether.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * 二维码的操作
 * 用之前打开权限
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;


    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.main_bt1);
        bt1.setOnClickListener(clickLis);
        bt2 = (Button) findViewById(R.id.main_bt2);
        bt2.setOnClickListener(clickLis);
        bt3 = (Button) findViewById(R.id.main_bt3);
        bt3.setOnClickListener(clickLis);
        bt4 = (Button) findViewById(R.id.main_bt4);
        bt4.setOnClickListener(clickLis);
    }

    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.main_bt1:
                    Intent intent1 = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent1, REQUEST_CODE);
                    break;
                case R.id.main_bt2:
                    Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent2.addCategory(Intent.CATEGORY_OPENABLE);
                    intent2.setType("image/*");
                    startActivityForResult(intent2, REQUEST_IMAGE);
                    break;
                case R.id.main_bt3:
                    Intent intent3 = new Intent(MainActivity.this, SecondActivity.class);
                    startActivityForResult(intent3, REQUEST_CODE);
                    break;
                case R.id.main_bt4:
                    Intent intent4 = new Intent(MainActivity.this, ThreeActivity.class);
                    startActivity(intent4);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(MainActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
