package com.cxp.lottie;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;

/**
 * 文 件 名: CodeActivity
 * 创 建 人: CXP
 * 创建日期: 2017-06-05 17:11
 * 描    述: 代码设置动画
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class CodeActivity extends AppCompatActivity {


    private TextView tv;
    private LottieAnimationView mLottieAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        tv= (TextView) findViewById(R.id.code_tv);
        mLottieAnimationView= (LottieAnimationView) findViewById(R.id.code_lav);

        LottieComposition.Factory.fromAssetFileName(this, "data.json", new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                mLottieAnimationView.setComposition(composition);
                mLottieAnimationView.setProgress(0.333f);

                mLottieAnimationView.playAnimation();
            }
        });


        mLottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setText(" 动画进度" + (int) (animation.getAnimatedFraction() * 100) + "%");
            }
        });
    }

    //点击事件
    public void clickLis(View view){
        switch (view.getId()) {
            case R.id.code_bt1:
                //开始动画
                startAnima();
                break;
            case R.id.code_bt2:
                //停止动画
                stopAnima();
                break;
        }

    }

    /*
     * 开始动画
     */
    private void startAnima() {

        boolean inPlaying = mLottieAnimationView.isAnimating();
        if (!inPlaying) {
            mLottieAnimationView.setProgress(0f);
            mLottieAnimationView.playAnimation();
        }
    }

    /*
     * 停止动画
     */
    private void stopAnima() {
        boolean inPlaying = mLottieAnimationView.isAnimating();
        if (inPlaying) {
            mLottieAnimationView.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLottieAnimationView.cancelAnimation();
    }

    //页面跳转
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
        context.startActivity(intent);
    }
}
