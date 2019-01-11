package com.example.sewonkim.imagecropper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView textView = null;
    GestureDetector gestureDetector = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
       // textView = (TextView)findViewById(R.id.txtView);

       // View gestureView = findViewById(R.id.gestureView);
//        gestureView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                gestureDetector.onTouchEvent(motionEvent);
//                return true;
//            }
//        });



        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                textView.append("onDown Call" + "\n");
                return true;
            }
            @Override
            public void onShowPress(MotionEvent motionEvent) {
                textView.append("onShowPress Call" + "\n");
            }
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                textView.append("onSingleTapUp Call" + "\n");
                return true;
            }
            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                textView.append("onScroll Call (" + v + " * " +  v1 + ")" + "\n");
                return true;
            }
            @Override
            public void onLongPress(MotionEvent motionEvent) {
                textView.append("onLongPress Call" + "\n");
            }
            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                textView.append("onFling Call (" + v + " * " +  v1 + ")" + "\n");
                return true;
            }
        });
    }

}
