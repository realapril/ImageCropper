package com.example.sewonkim.imagecropper;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

class PieChart extends View {
    Boolean mShowText;
    Integer mTextPos;

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0);

        init();

        try {
            mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
            mTextPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
        } finally {
            a.recycle();
        }

    }
    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }

  //  private List<Item> mData = new ArrayList<Item>();
    float mTextX, mTextY, mPointerX, mPointerY, mPointerSize ,mBounds;
    Paint mTextPaint, mPiePaint, mShadowPaint;
    RectF mShadowBounds;


    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        float mTextHeight = 0;
        if (mTextHeight == 0) {
            mTextHeight = mTextPaint.getTextSize();
        } else {
            mTextPaint.setTextSize(mTextHeight);
        }
        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.FILL);
        mPiePaint.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
        mShadowBounds = new RectF(0,0,0,0);
    }


        //실제로 파이차트를 그리는 부분
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the shadow
        canvas.drawOval(
                mShadowBounds,
                mShadowPaint
        );

        // Draw the label text
        mTextX = 0;
        mTextY = 0.3f;
        mBounds = 1;


//        canvas.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);
//
//        // Draw the pie slices
//        for (int i = 0; i < mData.size(); ++i) {
//            ClipData.Item it = mData.get(i);
//            mPiePaint.setShader(it.mShader);
//            canvas.drawArc(mBounds,
//                    360 - it.mEndAngle,
//                    it.mEndAngle - it.mStartAngle,
//                    true, mPiePaint);
//        }

        mPointerX =1;
        mPointerY =1;
        mPointerSize = 0.8f;

        // Draw the pointer
        canvas.drawLine(mTextX, mPointerY, mPointerX, mPointerY, mTextPaint);
        canvas.drawCircle(mPointerX, mPointerY, mPointerSize, mTextPaint);

    }
}

