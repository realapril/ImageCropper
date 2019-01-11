package com.example.sewonkim.imagecropper;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btn_imgView, btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_imgView =findViewById(R.id.btn_imageView);
        btn_view =findViewById(R.id.btn_view);

        //이미지뷰와 일반 뷰에서 각각 비트맵, 도형 그리기를 할것이다
        btn_imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ImgViewActivity.class);
                startActivity(intent);
        }});


        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ViewActivity.class);
                startActivity(intent);
        }});



    }




}
