package com.tctogether.taptargetview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;

/**
 * 按钮高亮
 * 地址：https://github.com/KeepSafe/TapTargetView
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));

        //屏幕初始化
        final Display display = getWindowManager().getDefaultDisplay();
        // 显示的图标  forBounds（）这个时候用
        final Drawable droid = ContextCompat.getDrawable(this, R.drawable.ic_android_black_24dp);
        // 自定义位置
        final Rect droidTarget = new Rect(0, 0, droid.getIntrinsicWidth() * 2, droid.getIntrinsicHeight() * 2);
        //  设置偏移量
        droidTarget.offset(display.getWidth() / 2, display.getHeight() / 2);


        // 高亮控件集合
        final TapTargetSequence sequence = new TapTargetSequence(this)
                .targets(
                        // Toolbar 的NavigationIcon
                        TapTarget.forToolbarNavigationIcon(toolbar, "返回", "返回按钮").targetCircleColor(R.color.colorAccent).id(1),
                        // Menu菜单图标高亮
                        TapTarget.forToolbarMenuItem(toolbar, R.id.search, "搜索", "搜索按钮")
                                .dimColor(android.R.color.black)  //不透明度30%
                                .outerCircleColor(R.color.colorAccent) //外面圆颜色
                                .targetCircleColor(android.R.color.black) //内圆颜色
                                .transparentTarget(true)  //设置目标是透明的
                                .textColor(android.R.color.black)  //字体颜色
                                .id(2), //当多个公用的时候要用Id
                        // 溢出工具类图标高亮
                        TapTarget.forToolbarOverflow(toolbar, "Menu溢出", "Menu溢出按钮").targetCircleColor(android.R.color.holo_green_dark).id(3),
                        // 自定义高亮位置
                        TapTarget.forBounds(droidTarget, "自定义", "自定义按钮")
                                .cancelable(false)
                                .icon(droid)
                                .id(4)
                )
                .listener(new TapTargetSequence.Listener() {
                    //集合监听
                    @Override
                    public void onSequenceFinish() {
                        //当集合执行完时候的监听
                        ((TextView) findViewById(R.id.educated)).setText("当集合执行完时候的监听!");
                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        //当取消时候的监听
                        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Dialog")
                                .setMessage("这是Dialog按钮的高亮")
                                .setPositiveButton("确定", null).show();
                        TapTargetView.showFor(dialog,
                                TapTarget.forView(dialog.getButton(DialogInterface.BUTTON_POSITIVE), "标题", "看看Id是几： " + lastTarget.id())
                                        .cancelable(false)
                                        .tintTarget(false), new TapTargetView.Listener() {
                                    @Override
                                    public void onTargetClick(TapTargetView view) {
                                        super.onTargetClick(view);
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

        // 文本处理
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.fab), "标题", "我是一个好人啊")
                .cancelable(false)
                .drawShadow(true)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                //启动
                sequence.start();
//                Toast.makeText(view.getContext(), "你点击了控件！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
                Toast.makeText(view.getContext(), "你点击了外圆!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Toast.makeText(view.getContext(), "关闭事件", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
