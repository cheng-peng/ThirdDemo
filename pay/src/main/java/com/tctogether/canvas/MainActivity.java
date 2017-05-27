package com.tctogether.canvas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.alipay.sdk.pay.ZFBPayManage;
import com.weixinpay.sdk.pay.WXPayManage;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements IPayResult {

    private EditText etPrice;
    private Button btPay;
    private RadioGroup mRadioGroup;

    //支付类型 1.微信 2.支付宝
    private int payType;
    //作品id
    private String drawid;
    //商品单价
    private String price;
    //微信支付管理类
    private WXPayManage wxPayManage;
    //支付宝支付管理类
    private ZFBPayManage zfbPayManage;
    //格式化钱单位（保留两位小数）
    private DecimalFormat df = new DecimalFormat("######0.00");
    //是否微信支付完成的跳转
    private boolean isWX;

    private String title;

    private AlertDialog dialog;
    private AlertDialog.Builder alertView;

    /**
     * 弹出支付失败提示
     *
     * @param msg
     */
    private void showAlert(String msg) {
        alertView = new AlertDialog.Builder(MainActivity.this).setTitle("提示");//设置对话框标题
        alertView.setMessage(msg);//设置显示的内容
        alertView.setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                dialog.dismiss();
            }
        });
        dialog = alertView.show();//在按键响应事件中显示此对话框
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrice = (EditText) findViewById(R.id.main_et_price);
        btPay = (Button) findViewById(R.id.main_bt_pay);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_rg);

        //初始化微信管理类
        wxPayManage = new WXPayManage(this);
        //初始化支付宝管理类
        zfbPayManage = new ZFBPayManage(this);
        //初始化数据
        initData();

        //立即支付
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPrice.getText() != null && etPrice.getText().toString().trim().length() != 0) {
                    price = etPrice.getText().toString();
                } else {
                    if (payType == 1) {
                        //微信最小是1
                        price = "1";
                    } else {
                        price = "0.01";
                    }
                }
                //商品名称
                String cName = "话费充值" + price + "元";
                if (payType == 1) {
                    //微信支付
                    wxPayManage.sendPayRequest(cName, price, payType, "978515743", "5555555");
                } else {
                    //支付宝支付
                    zfbPayManage.sendPayRequest(cName, price);
                }
            }
        });

        //单选按钮改变
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.main_rb_ali:
                        payType = 2;
                        break;
                    case R.id.main_rb_weixin:
                        payType = 1;
                        break;
                }
            }
        });
    }

    //初始化数据
    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            isWX = intent.getBooleanExtra("isWX", false);
            if (!isWX) //如果不是微信跳转返回
            {
                payType = intent.getIntExtra("payType", 1);
                title = intent.getStringExtra("title");
                price = String.valueOf(intent.getDoubleExtra("jine", 0.00f));
                drawid = intent.getStringExtra("drawid");
            } else {
                //微信支付之后返回
                payType = WXPayManage.commodityInfo.getPayType();
                price = String.valueOf(Integer.parseInt(WXPayManage.commodityInfo.getPrice()) / (double) 100);
                drawid = WXPayManage.commodityInfo.getDrawid();
            }
            String priceStr = price + "元";
            Log.e("CXP", "title:" + title + "\nprice:" + priceStr + "\ndrawid:" + drawid);


            //判断微信支付成功的跳转
            if (isWX) {
                int errCode = getIntent().getIntExtra("errCode", -1);
                if (errCode == 0) {
                    showAlert("支付成功！");
                } else if (errCode == -1) {
                    showAlert("支付失败！");
                } else if (errCode == -2) {
                    showAlert("支付失败！");
//                    showAlert("用户已取消支付！");
                }
            }
        }
    }

    @Override
    public void payResult(boolean b, int type) {
        if (b) {
            showAlert("支付成功！");
        } else {
            showAlert("支付失败！");
        }
    }
}
