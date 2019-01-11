package com.example.sewonkim.imagecropper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class FreeLineView extends View {



    public ArrayList<FreeLineInfo> mArInfo = new ArrayList <FreeLineInfo>();

    private Paint mPaint;

    private int background;



    public FreeLineView(Context context){

        super(context);

        mPaint = new Paint();

        mPaint.setColor(Color.BLUE);

        mPaint.setStrokeWidth(5);

        mPaint.setAntiAlias(true);

        background = Color.LTGRAY;



    }



    public FreeLineView(Context c, AttributeSet attrs)

    {



        super(c, attrs);



        TypedArray typeArray = c.obtainStyledAttributes(attrs,  R.styleable.FreeLineView);

        mPaint = new Paint();

        mPaint.setColor( Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czcolor ) ) );

        mPaint.setStrokeWidth( typeArray.getInt( R.styleable.FreeLineView_czstrokeWidth, 1) );

        mPaint.setAntiAlias( typeArray.getBoolean( R.styleable.FreeLineView_czantiAlias, false) );

        background = Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czbackground ) );



    }





    public FreeLineView(Context c, AttributeSet attrs, int defStyle)

    {



        super(c, attrs, defStyle);



        TypedArray typeArray = c.obtainStyledAttributes(attrs,  R.styleable.FreeLineView);

        mPaint = new Paint();

        mPaint.setColor( Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czcolor) ) );

        mPaint.setStrokeWidth( typeArray.getInt( R.styleable.FreeLineView_czstrokeWidth, 1) );

        mPaint.setAntiAlias( typeArray.getBoolean( R.styleable.FreeLineView_czantiAlias, false) );

        background = Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czbackground ) );

    }


//실제로 그려지는 부분
    public void onDraw(Canvas canvas){

        canvas.drawColor(background);

        for(int i=0; i < mArInfo.size(); i++){

            if( mArInfo.get(i).draw){

                canvas.drawLine(mArInfo.get(i-1).x, mArInfo.get(i-1).y, mArInfo.get(i).x, mArInfo.get(i).y, mPaint);

            }

        }

    }



    public boolean onTouchEvent(MotionEvent event){



        if(event.getAction() == MotionEvent.ACTION_DOWN){

            mArInfo.add(new FreeLineInfo(event.getX(), event.getY(), false));



            return true;

        }



        if(event.getAction() == MotionEvent.ACTION_MOVE ){

            mArInfo.add(new FreeLineInfo(event.getX(), event.getY(), true));

            invalidate();

            return true;

        }



        return false;

    }



    public Paint getPaint(){



        return mPaint;

    }

}