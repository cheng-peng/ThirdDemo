package com.cxp.alarm;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zz on 2017/4/12.
 */

public class RangeView extends View {

    private OnSeekChangeListener onSeekChangeListener;
    private int startValue;
    private int preValue;
    private int prePixel = 50;
    private int sectionCount;
    private int lineColor;
    private int lineWidth;
    private int thumbColor;
    private int arrColor;
    private boolean unlimited;
    private int thumbRadius;
    private int textSize;
    private int space;
    private Paint paint;
    private int leftIndex;
    private int rightIndex;
    private Bitmap circle;
    private RectF leftRect;
    private RectF rightRect;
    private float sx;
    private boolean dispatched;


    public RangeView(Context context) {
        this(context, null);
    }

    public RangeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setOnSeekChangeListener(OnSeekChangeListener onSeekChangeListener) {
        this.onSeekChangeListener = onSeekChangeListener;
    }

    public int getLeftValue() {
        return startValue + leftIndex * preValue;
    }

    public int getRightValue() {
        if (unlimited && rightIndex == sectionCount) {
            return -1;
        }
        return startValue + rightIndex * preValue;
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RangeView);
        startValue = a.getInt(R.styleable.RangeView_startValue, 0);
        preValue = a.getInt(R.styleable.RangeView_preValue, 100);
        sectionCount = a.getInt(R.styleable.RangeView_sectionCount, 5);
        lineColor = a.getColor(R.styleable.RangeView_lineColor, Color.argb(255, 100, 100, 100));
        lineWidth = a.getDimensionPixelSize(R.styleable.RangeView_lineWidth, 2);
        thumbColor = a.getColor(R.styleable.RangeView_thumbColor, getResources().getColor(R.color.colorAccent));
        thumbRadius = a.getDimensionPixelSize(R.styleable.RangeView_thumbRadius, 15);
        arrColor = a.getColor(R.styleable.RangeView_arrColor, Color.WHITE);
        unlimited = a.getBoolean(R.styleable.RangeView_unlimited, false);
        textSize = a.getDimensionPixelSize(R.styleable.RangeView_textSize, 16);
        space = a.getDimensionPixelSize(R.styleable.RangeView_space, 10);
        leftIndex = 0;
        rightIndex = sectionCount;
        paint = new Paint();
        paint.setAntiAlias(true);
        drawCircle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getPaddingTop() + getPaddingBottom() + thumbRadius * 2 + space + textSize;
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            prePixel = (maxWidth - getPaddingLeft() - getPaddingRight() - thumbRadius * 2) / sectionCount;
            width = maxWidth;
        } else {
            width = getPaddingLeft() + getPaddingRight() + thumbRadius * 2 + prePixel * sectionCount;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //底线
        int sx = thumbRadius + getPaddingLeft();
        int ex = getWidth() - thumbRadius - getPaddingRight();
        int y = thumbRadius + getPaddingTop();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(lineWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(sx, y, ex, y, paint);
        //选中段线
        paint.setColor(lineColor);
        canvas.drawLine(sx + leftIndex * prePixel, y, sx + rightIndex * prePixel, y, paint);
        if (leftRect == null) {
            leftRect = new RectF(getPaddingLeft() + leftIndex * prePixel,
                    getPaddingTop(), getPaddingLeft() + leftIndex * prePixel + thumbRadius * 2,
                    getPaddingTop() + thumbRadius * 2);
        } else {
            leftRect.set(getPaddingLeft() + leftIndex * prePixel,
                    getPaddingTop(), getPaddingLeft() + leftIndex * prePixel + thumbRadius * 2,
                    getPaddingTop() + thumbRadius * 2);
        }
        if (rightRect == null) {
            rightRect = new RectF(getPaddingLeft() + rightIndex * prePixel,
                    getPaddingTop(), getPaddingLeft() + rightIndex * prePixel + thumbRadius * 2,
                    getPaddingTop() + thumbRadius * 2);
        } else {
            rightRect.set(getPaddingLeft() + rightIndex * prePixel,
                    getPaddingTop(), getPaddingLeft() + rightIndex * prePixel + thumbRadius * 2,
                    getPaddingTop() + thumbRadius * 2);
        }
        //圆
        canvas.drawBitmap(circle, leftRect.left, leftRect.top, null);
        canvas.drawBitmap(circle, rightRect.left, rightRect.top, null);

        //文字
        paint.setTextSize(textSize);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        y = getPaddingTop() + thumbRadius * 2 + space + textSize;
        String text;
        for (int i = 0; i < sectionCount + 1; i++) {
            if (i == sectionCount && unlimited) {
                text = "无限";
            } else {
                text = String.valueOf(startValue + preValue * i);
            }
            float textWidth = paint.measureText(text);
            float x = sx + i * prePixel - textWidth / 2;
            canvas.drawText(text, x, y, paint);
        }
    }


    private void drawCircle() {
        int diameter = thumbRadius * 2;
        circle = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circle);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(thumbColor);
        paint.setStrokeWidth(1);
        canvas.drawCircle(thumbRadius, thumbRadius, thumbRadius, paint);

        paint.setColor(arrColor);
        paint.setStrokeWidth(2);
        float x1 = Math.round(thumbRadius / 3f);
        float y1 = thumbRadius;
        float x2 = thumbRadius * 6 / 7f;
        float y2 = Math.round(thumbRadius / 2f);
        canvas.drawLine(x1, y1, x2, y2, paint);
        canvas.drawLine(x1, y1, x2, thumbRadius * 2 - y2, paint);
        canvas.drawLine(thumbRadius * 2 - x1, y1, thumbRadius * 2 - x2, y2, paint);
        canvas.drawLine(thumbRadius * 2 - x1, y1, thumbRadius * 2 - x2, thumbRadius * 2 - y2, paint);
    }

    boolean l;
    boolean r;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (!dispatched) {
            l = leftRect.contains(x, y);
            r = rightRect.contains(x, y);
        }
        if (l || r || dispatched) {
            dispatched = true;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sx = x;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float dx = x - sx;
                    if (Math.abs(dx) >= prePixel / 2) {
                        if (dx > 0) {
                            if (l && leftIndex + 1 < rightIndex) {
                                leftIndex++;
                                sx = x;
                                onChange();
                            } else if (r && rightIndex < sectionCount) {
                                rightIndex++;
                                sx = x;
                                onChange();
                            }
                        } else {
                            if (l && leftIndex > 0) {
                                leftIndex--;
                                sx = x;
                                onChange();
                            } else if (r && rightIndex - 1 > leftIndex) {
                                rightIndex--;
                                sx = x;
                                onChange();
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    dispatched = false;
                    break;
            }
            return true;
        }
        return false;
    }

    private void onChange() {
        invalidate();
        if (onSeekChangeListener != null) {
            onSeekChangeListener.onChanged(getLeftValue(), getRightValue());
        }
    }

    public interface OnSeekChangeListener {
        void onChanged(int leftValue, int rightValue);
    }
}
