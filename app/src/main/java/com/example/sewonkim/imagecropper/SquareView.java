package com.example.sewonkim.imagecropper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SquareView extends View {
    public ArrayList<SquarePreInfo> dotsInfo = new ArrayList <SquarePreInfo>();
    private Paint mPaint, mPaint2, mPaint3, clearPaint;
    private int background, dotcount;
    private int w;
    private int h;
    private boolean erase = false;
    private Rect rectangle;

    //XML형식 리소스가 아닌 자바코드에서 직접 뷰 객체 생성할시 사용.
    public SquareView(Context context){

        super(context);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        //rectangle = new Rect()


        background = Color.LTGRAY;

    }

    //리소스에서 XML에서 정의한 뷰의 속성값들이 attrs객체에 저장되어 전달된다. 뷰가 만들어질때 사용된다.
    public SquareView(Context c, AttributeSet attrs)

    {
        super(c, attrs);

        TypedArray typeArray = c.obtainStyledAttributes(attrs,  R.styleable.SquareView);

        mPaint = new Paint();
        mPaint.setColor( Color.parseColor( typeArray.getString( R.styleable.SquareView_sqcolor ) ) );
        mPaint.setStrokeWidth( typeArray.getInt( R.styleable.SquareView_sqstrokeWidth, 1) );
        mPaint.setAntiAlias( typeArray.getBoolean( R.styleable.SquareView_sqantiAlias, false) );
        mPaint.setStyle(Paint.Style.STROKE);

        background = Color.parseColor( typeArray.getString( R.styleable.SquareView_sqbackground ) );

    }

    //defStyle은 뷰에 어떤 스타일 테마를 적용할지 나타내는것. defStyle = 0 일시 스타일적용안되고
    //리소스XML에서 android:theme에서 스타일 적용 가능
    public SquareView(Context c, AttributeSet attrs, int defStyle)

    {
        super(c, attrs, defStyle);

        TypedArray typeArray = c.obtainStyledAttributes(attrs,  R.styleable.FreeLineView);

        mPaint = new Paint();
        mPaint.setColor( Color.parseColor( typeArray.getString( R.styleable.SquareView_sqcolor ) ) );
        mPaint.setStrokeWidth( typeArray.getInt( R.styleable.SquareView_sqstrokeWidth, 1) );
        mPaint.setAntiAlias( typeArray.getBoolean( R.styleable.SquareView_sqantiAlias, false) );
        mPaint.setStyle(Paint.Style.STROKE);

        background = Color.parseColor( typeArray.getString( R.styleable.SquareView_sqbackground ) );

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
        switch (dotcount) {
            case 0:
                break;
            case 1:
                canvas.drawPoint(dotsInfo.get(0).x, dotsInfo.get(0).y,mPaint);
                break;
            case 2:
                canvas.drawPoint(dotsInfo.get(1).x, dotsInfo.get(1).y,mPaint);
                canvas.drawLine(dotsInfo.get(0).x, dotsInfo.get(0).y ,dotsInfo.get(1).x, dotsInfo.get(1).y, mPaint);
               // canvas.drawRect(rectangle,mPaint);
                //두 점사이 거리
                float distance = (float) Math.sqrt(
                        Math.pow(dotsInfo.get(1).x-dotsInfo.get(0).x, 2) +
                                Math.pow(dotsInfo.get(1).y-dotsInfo.get(0).y, 2) );
                //자동으로 사각형을 그림
                canvas.drawRect(w/3, h/3, (w/3 + distance*3), (h/3 + distance*3), mPaint);
                break;
            case 3:
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                dotcount = 0;
                dotsInfo.clear();
                break;
        }

    }



    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){
           // dotsInfo.add(new FreeLineInfo(event.getX(), event.getY(), false));
            //onDraw메소드 호출
            //invalidate();

            return true;

        }


        if(event.getAction() == MotionEvent.ACTION_MOVE ){
//            dotcount = dotcount+1;
//            Log.d("도트",String.valueOf(dotcount));
//            dotsInfo.add(new SquarePreInfo(event.getX(), event.getY(), dotcount, true));
//            Log.d("움직이는 좌표",String.valueOf(event.getX())+"  :  "+String.valueOf(event.getY()));
//            //onDraw메소드 호출
//            invalidate();

            return true;

        }

        if (event.getAction() == MotionEvent.ACTION_UP){

            erase = true;
            dotcount = dotcount +1;
            dotsInfo.add(new SquarePreInfo(event.getX(), event.getY(), dotcount,true));
            Log.d("도트",String.valueOf(dotcount));
            invalidate();
            return true;
        }

        return false;

    }



    public Paint getPaint(){

        return mPaint;

    }
}
