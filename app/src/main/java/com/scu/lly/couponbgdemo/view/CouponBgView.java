package com.scu.lly.couponbgdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.scu.lly.couponbgdemo.R;

/**
 * 优惠券背景
 * Created by lusheep on 2016/7/9.
 */
public class CouponBgView extends LinearLayout{

    private Paint mPaint;

    private int mWidth;
    private int mHeight;
    /**
     * 圆与圆之间的间距
     */
    private float mGapSize = 8;
    /**
     * 圆的半径
     */
    private float mRadius = 10;
    /**
     * 垂直方向圆或三角形的数量
     */
    private int mVerticalCount;

    /**
     * 垂直方向初始偏移距离
     */
    private int mVerticalInitSize;

    /**
     * 水平方向圆或三角形的数量
     */
    private int mHorizontalCount;
    /**
     * 水平方向初始偏移量
     */
    private int mHorizontalInitSize;
    /**
     * 默认的垂直方向的样式为none，即不进行绘制
     */
    private static final int DEFAULT_VERTICAL_STYLE_NONE = 0;
    /**
     * 默认的水平向的样式为none，即不进行绘制
     */
    private static final int DEFAULT_HORIZONTAL_STYLE_NONE = 0;
    //水平或垂直方向的边缘类型
    private int vertical_style = DEFAULT_VERTICAL_STYLE_NONE;
    private int horizontal_style = DEFAULT_HORIZONTAL_STYLE_NONE;

    public CouponBgView(Context context) {
        super(context);
        init(context, null);
    }

    public CouponBgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CouponBgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);//设置为白色，需要根据父布局的背景色进行调整，否则绘制的这一块会比较显眼突出

        setWillNotDraw(false);

        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponStyle);
            vertical_style = ta.getInt(R.styleable.CouponStyle_vertical_style, DEFAULT_VERTICAL_STYLE_NONE);
            horizontal_style = ta.getInt(R.styleable.CouponStyle_horizontal_style, DEFAULT_HORIZONTAL_STYLE_NONE);
            ta.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(vertical_style == 1){//如果垂直方向是半圆形
            drawVerticalCircle(canvas);
        }else if(vertical_style == 2){//垂直方向是三角形
            drawVerticalTriangle(canvas);
        }
        if(horizontal_style == 1){//如果水平方向是半圆形
            drawHorizontalCircle(canvas);
        }else if(horizontal_style == 2){//如果水平方向是三角形
            drawHorizontalTriangle(canvas);
        }
    }

    /**
     * 在水平方向上绘制三角形
     * @param canvas
     */
    private void drawHorizontalTriangle(Canvas canvas) {
        //先计算出水平方向上的数量
        calculateHorizontalCount(0);
        Path path = new Path();
        float x = 0;
        //绘制上面部分
        for(int i = 0; i < mHorizontalCount; i++){
            path.reset();
            x = mHorizontalInitSize + i * 2 * mRadius;
            path.moveTo(x, 0);
            x += mRadius;
            path.lineTo(x, mRadius);
            x += mRadius;
            path.lineTo(x, 0);
            path.close();
            canvas.drawPath(path, mPaint);
        }
        //绘制下面部分
        x = 0;
        for(int i = 0; i < mHorizontalCount; i++){
            path.reset();
            x = mHorizontalInitSize + i * 2 * mRadius;
            path.moveTo(x, mHeight);
            x += mRadius;
            path.lineTo(x, mHeight - mRadius);
            x += mRadius;
            path.lineTo(x, mHeight);
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    /**
     * 在水平方向上绘制圆形
     * @param canvas
     */
    private void drawHorizontalCircle(Canvas canvas) {
        //先计算出水平方向上的数量
        calculateHorizontalCount(mGapSize);
        float x = mHorizontalInitSize + mGapSize + mRadius;
        //先绘制上面部分
        for(int i = 0; i < mHorizontalCount; i++){
            canvas.drawCircle(x, 0, mRadius, mPaint);
            x += 2 * mRadius + mGapSize;
        }
        //再绘制下面部分
        x = mHorizontalInitSize + mGapSize + mRadius;
        for(int i = 0; i < mHorizontalCount; i++){
            canvas.drawCircle(x, mHeight, mRadius, mPaint);
            x += 2 * mRadius + mGapSize;
        }
    }

    /**
     * 在垂直方向绘制三角形
     * @param canvas
     */
    private void drawVerticalTriangle(Canvas canvas) {
        //计算一下三角形的数量和初始距离
        calculateVerticalCount(0);
        Path path = new Path();
        float y = 0;
        //先画左边
        for(int i = 0; i < mVerticalCount; i++){
            path.reset();
            y = mVerticalInitSize + i * 2 * mRadius;
            path.moveTo(0, y);
            y += mRadius;
            path.lineTo(mRadius, y);
            y += mRadius;
            path.lineTo(0, y);
            path.close();
            canvas.drawPath(path, mPaint);
        }
        //再画右边
        y = 0;
        for(int i = 0; i < mVerticalCount; i++){
            path.reset();
            y = mVerticalInitSize + i * 2 * mRadius;
            path.moveTo(mWidth, y);
            y += mRadius;
            path.lineTo(mWidth - mRadius, y);
            y += mRadius;
            path.lineTo(mWidth, y);
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    /**
     * 在垂直方向绘制半圆形
     * @param canvas
     */
    private void drawVerticalCircle(Canvas canvas) {
        //计算一下圆形的数量和初始偏移距离
        calculateVerticalCount(mGapSize);
        //这次使用画弧来绘制出圆形
        RectF rectF = new RectF();
        //先画左边
        for(int i = 0; i < mVerticalCount; i++){
            rectF.left = -mRadius;
            rectF.top = mVerticalInitSize + mGapSize * (i + 1) + i * 2 * mRadius;
            rectF.right =  mRadius;
            rectF.bottom = rectF.top + 2 * mRadius;
            canvas.drawArc(rectF, -90, 180, false, mPaint);
        }
        //再画右边
        for(int i = 0; i < mVerticalCount; i++){
            rectF.left = mWidth - mRadius;
            rectF.top = mVerticalInitSize + mGapSize * (i + 1) + i * 2 * mRadius;
            rectF.right = rectF.left + 2 * mRadius;
            rectF.bottom = rectF.top + 2 * mRadius;
            canvas.drawArc(rectF, 90, 180, false, mPaint);
        }
    }

    /**
     * 计算垂直方向需要画圆或三角形的数量
     * @param gapSize 每个圆形或三角形之间的间距
     */
    private void calculateVerticalCount(float gapSize){
        mVerticalCount = (int) ((mHeight - gapSize) / (2 * mRadius + gapSize));
        mVerticalInitSize = (int) ((mHeight - (2 * mRadius * mVerticalCount + (mVerticalCount + 1) * gapSize)) / 2);
    }

    /**
     * 计算水平方向上圆或三角形的数量和初始偏移量
     * @param gapSize 每个圆形或三角形之间的间距
     */
    private void calculateHorizontalCount(float gapSize) {
        mHorizontalCount = (int) ((mWidth - gapSize) / (2 * mRadius + gapSize));
        mHorizontalInitSize = (int) ((mWidth - (2 * mRadius * mHorizontalCount + (mHorizontalCount + 1) * gapSize)) / 2);
    }
}
