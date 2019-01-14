package com.example.sewonkim.imagecropper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class FreeLineView extends View {

    public ArrayList<FreeLineInfo> mArInfo = new ArrayList <FreeLineInfo>();
    private Paint mPaint, mPaint2, mPaint3, clearPaint;
    private int background, dot;
    private int w;
    private int h;
    private boolean erase = false;


    //XML형식 리소스가 아닌 자바코드에서 직접 뷰 객체 생성할시 사용.
    public FreeLineView(Context context){

        super(context);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        background = Color.LTGRAY;

        mPaint2 = new Paint();
        mPaint.setColor( Color.parseColor( "#FF0000" ) );
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        mPaint3 = new Paint();
        mPaint.setColor( Color.parseColor( "#000000" ) );
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

    }

    //리소스에서 XML에서 정의한 뷰의 속성값들이 attrs객체에 저장되어 전달된다. 뷰가 만들어질때 사용된다.
    public FreeLineView(Context c, AttributeSet attrs)

    {
        super(c, attrs);

        TypedArray typeArray = c.obtainStyledAttributes(attrs,  R.styleable.FreeLineView);

        mPaint = new Paint();
        mPaint.setColor( Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czcolor ) ) );
        mPaint.setStrokeWidth( typeArray.getInt( R.styleable.FreeLineView_czstrokeWidth, 1) );
        mPaint.setAntiAlias( typeArray.getBoolean( R.styleable.FreeLineView_czantiAlias, false) );

        mPaint2 = new Paint();
        mPaint2.setColor( Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czcolor2 ) ) );
        mPaint2.setStrokeWidth( typeArray.getInt( R.styleable.FreeLineView_czstrokeWidth, 1) );
        mPaint2.setAntiAlias( typeArray.getBoolean( R.styleable.FreeLineView_czantiAlias, false) );

        background = Color.parseColor( typeArray.getString( R.styleable.FreeLineView_czbackground ) );

    }

    //defStyle은 뷰에 어떤 스타일 테마를 적용할지 나타내는것. defStyle = 0 일시 스타일적용안되고
    //리소스XML에서 android:theme에서 스타일 적용 가능
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

    //뷰의 사이즈를 가져오는 메소드 (화면 해상도 아님)
    //onDraw 이전에 호출됨
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.w = w;
        this.h = h;
        Log.d("화면사이즈", String.valueOf(w)+"  "+String.valueOf(h));
        super.onSizeChanged(w, h, oldw, oldh);
    }


    //실제로 커스텀뷰에 출력되는 형식을 정의하는 메소드
    public void onDraw(Canvas canvas){

        canvas.drawColor(background);

        for(int i=0; i < mArInfo.size(); i++){

            if( mArInfo.get(i).draw){

                //시작점 두개, 끝나는점 두개
//                canvas.drawLine(dotsInfo.get(i-1).x, dotsInfo.get(i-1).y, dotsInfo.get(i).x, dotsInfo.get(i).y, mPaint);


                canvas.drawCircle(mArInfo.get(i).x, mArInfo.get(i).y,3,mPaint);
//                canvas.drawPoint(dotsInfo.get(i).x, dotsInfo.get(i).y,mPaint);
                canvas.drawLine(mArInfo.get(i).x, 0,mArInfo.get(i).x, h, mPaint);
                canvas.drawLine(0, mArInfo.get(i).y, w, mArInfo.get(i).y, mPaint);

//                if (i>0){
//
//                canvas.drawPoint(dotsInfo.get(i-1).x, dotsInfo.get(i-1).y,mPaint2);
//                canvas.drawLine(dotsInfo.get(i-1).x, 0,dotsInfo.get(i-1).x, h, mPaint2);
//                canvas.drawLine(0, dotsInfo.get(i-1).y, w, dotsInfo.get(i-1).y, mPaint2);
//                }

                //이거안됨. 화면이 까매짐
//                if (erase){
//                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//                }


            }

        }

    }



    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mArInfo.add(new FreeLineInfo(event.getX(), event.getY(), false));
            //onDraw메소드 호출
            //invalidate();
            erase = false;
            return true;

        }


        if(event.getAction() == MotionEvent.ACTION_MOVE ){

            mArInfo.add(new FreeLineInfo(event.getX(), event.getY(), true));
            Log.d("움직이는 좌표",String.valueOf(event.getX())+"  :  "+String.valueOf(event.getY()));
            //onDraw메소드 호출
            invalidate();

            return true;

        }

        if (event.getAction() == MotionEvent.ACTION_UP){

            erase = true;
            dot = dot+1;
            Log.d("도트",String.valueOf(dot));

            return true;
        }

        return false;

    }



    public Paint getPaint(){

        return mPaint;

    }

}