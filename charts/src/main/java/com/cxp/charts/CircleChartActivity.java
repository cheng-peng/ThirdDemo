package com.cxp.charts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cxp.charts.view.circle_chart.CircleChart01View;
import com.cxp.charts.view.circle_chart.CircleChart02View;
import com.cxp.charts.view.circle_chart.CircleChart04View;

/**
 * 文 件 名: CircleChartActivity
 * 创 建 人: CXP
 * 创建日期: 2017-02-06 14:45
 * 描    述: 圆形图表
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class CircleChartActivity extends AppCompatActivity {

    //半圆
    private CircleChart01View halfchart = null;
    //圆
    private CircleChart02View chart = null;
    //半圆
    private CircleChart04View halfchart2 = null;

    //进度/状态
    private TextView process = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_chart);

        this.setTitle("圆/半圆图(Circle Chart)");

        //初始化视图
        initView();
    }

    //初始化视图
    private void initView()
    {
        halfchart= (CircleChart01View) findViewById(R.id.halfcircle_view);
        chart= (CircleChart02View) findViewById(R.id.circle_view);
        halfchart2= (CircleChart04View) findViewById(R.id.halfcircle_view2);
        process= (TextView) findViewById(R.id.tv_process);

        SeekBar seekBar = (SeekBar) this.findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //赋值
                process.setText(Integer.toString(i));

                halfchart.setPercentage(i);
                halfchart.chartRender();
                halfchart.invalidate();

                chart.setPercentage(i);
                chart.chartRender();
                chart.invalidate();

                halfchart2.setPercentage(i);
                halfchart2.chartRender();
                halfchart2.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
