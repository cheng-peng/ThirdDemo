package com.cxp.charts.view.circle_chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

import org.xclcharts.chart.CircleChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.view.GraphicalView;

import java.util.LinkedList;
import java.util.List;

/**
 * 文 件 名: CircleChart03View
 * 创 建 人: CXP
 * 创建日期: 2017-02-06 15:04
 * 描    述: 图形图例子(半圆)
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class CircleChart03View extends GraphicalView {

    private String TAG = "CircleChart03View";
    private CircleChart chart = new CircleChart();

    private List<PieData> mlPieData = new LinkedList<PieData>();
    private String mDataInfo = "";

    public CircleChart03View(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setPercentage(0);
        chartRender();
    }

    public CircleChart03View(Context context, AttributeSet attrs){
        super(context, attrs);
        setPercentage(0);
        chartRender();
    }

    public CircleChart03View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPercentage(0);
        chartRender();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        //xml中的设置: android:layout_height="200dip"
        // int chartHeight = DensityUtil.dip2px(getContext(), 200 / 2); //100dip
        chart.setChartRange(w ,h); // + chartHeight);
    }


    public void chartRender()
    {
        try {
            //设置附加信息
            chart.setAttributeInfo(mDataInfo);

            //半圆方式显示
            chart.setCircleType(XEnum.CircleType.HALF);

            //设置图表数据源
            chart.setDataSource(mlPieData);

            chart.hideInnerFill();

            chart.showRoundBorder();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //百分比
    public void setPercentage(int per)
    {
        if(per < 50)
        {
            mDataInfo = "轻松搞定";
            chart.getLabelPaint().setColor(Color.WHITE);
            chart.getDataInfoPaint().setColor(Color.WHITE);

        }else if(per < 70){
            mDataInfo = "充满活力";
            chart.getLabelPaint().setColor(Color.rgb(85, 41, 124));
            chart.getDataInfoPaint().setColor(Color.WHITE);
        }else{
            mDataInfo = "不堪重负";
            chart.getLabelPaint().setColor(Color.RED);
            chart.getDataInfoPaint().setColor(Color.RED);
        }
        //PieData(标签，百分比，在饼图中对应的颜色)
        mlPieData.clear();
        mlPieData.add(new PieData(Integer.toString(per)+"%",per,Color.rgb(72, 201, 176)));
    }

    @Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }
}
