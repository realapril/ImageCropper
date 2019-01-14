package com.example.sewonkim.imagecropper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.TextView;

public class GestureActivity extends AppCompatActivity {
    //TODO: https://developer.android.com/training/gestures/scroll
    //TODO: https://developer.android.com/training/gestures/scale

    private static final String DEBUG_TAG = "Velocity";

    private VelocityTracker mVelocityTracker = null;
    TextView txtv, txtv2, txtv3, txtv4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        txtv = findViewById(R.id.textView);
        txtv2 = findViewById(R.id.textView2);
        txtv3 = findViewById(R.id.textView3);
        txtv4 = findViewById(R.id.textView4);

    }


    //이동속도, 좌표 트래킹 가능
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int index = event.getActionIndex();
        int action = event.getActionMasked();
        // Get the pointer ID
        int pointerId = event.getPointerId(index); //인덱스 어차피 0만찍힘
        // Use the pointer ID to find the index of the active pointer
        // and fetch its position
        int pointerIndex = event.findPointerIndex(pointerId); //이거랑 그냥 인덱스 차이는?

        Log.d("인덱스: 포인터인덱스",String.valueOf(index)+"  :  "+String.valueOf(pointerIndex));


        if (event.getPointerCount() >= 2) {
            Log.d(DEBUG_TAG,"Multitouch event");
            txtv4.setText("멀티터치");
            // The coordinates of the current screen contact, relative to
            // the responding View or Activity.
            // Get the pointer's current position
            float x = event.getX(pointerIndex); //그냥 인덱스 가져오면 중간에 pointerIndex out of range 에러남
            float y = event.getY(pointerIndex);
            txtv.setText("좌표:"+Float.toString(x)+" , "+Float.toString(y));

        } else {
            // Single touch event
            Log.d(DEBUG_TAG,"Single touch event");
            txtv4.setText("싱글터치");
            // Get the pointer's current position
            float x = event.getX(pointerIndex);
            float y = event.getY(pointerIndex);
            txtv.setText("좌표:"+Float.toString(x)+" , "+Float.toString(y));
        }

        switch (action) {

            case MotionEvent.ACTION_DOWN: //눌림
                txtv3.setText("ACTION_DOWN");
                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                txtv3.setText("ACTION_MOVE");
                mVelocityTracker.addMovement(event);
                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000);
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
//                Log.d("", "X velocity: " + mVelocityTracker.getXVelocity(pointerId));
//                Log.d("", "Y velocity: " + mVelocityTracker.getYVelocity(pointerId));
                txtv2.setText("이동속도:"+Float.toString(mVelocityTracker.getXVelocity(pointerId))+" , "+Float.toString(mVelocityTracker.getYVelocity(pointerId)));

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //Log.d(DEBUG_TAG,"포인터다운");
                txtv3.setText("ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(DEBUG_TAG,"손뗌");
                txtv3.setText("Action_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //Log.d(DEBUG_TAG,"포인터업");
                txtv3.setText("ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                txtv3.setText("ACTION_OUTSIDE");
                Log.d(DEBUG_TAG,"바깥");
                break;
            case MotionEvent.ACTION_CANCEL: //action_up과 비슷. 근데 여기에 어떤 액션을 실행시키지마라
                txtv3.setText("ACTION_CANCEL");
                // Return a VelocityTracker object back to be re-used by others.
                // 여기서 자꾸 에러남.
                //mVelocityTracker.recycle();
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.d(DEBUG_TAG,"스크롤");
                break;
        }
        return true;


    }
}
