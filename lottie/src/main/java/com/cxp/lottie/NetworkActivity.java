package com.cxp.lottie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文 件 名: NetworkActivity
 * 创 建 人: CXP
 * 创建日期: 2017-06-05 17:25
 * 描    述: 网络设置动画
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class NetworkActivity extends AppCompatActivity {

    private LottieAnimationView mLottieAnimationView;

    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        mLottieAnimationView= (LottieAnimationView) findViewById(R.id.network_lav);
        loadUrl("http://www.cxp521.com/data.json");
    }

    private void loadUrl(String url) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } catch (IllegalArgumentException e) {
            return;
        }


        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {

            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                }

                try {
                    JSONObject json = new JSONObject(response.body().string());
                    LottieComposition.Factory
                            .fromJson(getResources(), json, new OnCompositionLoadedListener() {
                                @Override
                                public void onCompositionLoaded(@Nullable LottieComposition composition) {
                                    setComposition(composition);
                                }
                            });
                } catch (JSONException e) {
                }
            }
        });
    }

    private  void setComposition(LottieComposition composition){
        mLottieAnimationView.setProgress(0);
        mLottieAnimationView.loop(true);
        mLottieAnimationView.setComposition(composition);
        mLottieAnimationView.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLottieAnimationView.cancelAnimation();
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NetworkActivity.class);
        context.startActivity(intent);
    }
}
