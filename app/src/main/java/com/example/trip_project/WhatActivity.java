package com.example.trip_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class WhatActivity extends AppCompatActivity {

    Button btnConf; //다음 액티비티로 넘어가는 버튼

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what);



        btnConf = (Button) findViewById(R.id.button_what);
        btnConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음 액티비티로 가는 것
                //Intent
                Intent intent = new Intent(WhatActivity.this, ConfirmActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });
    }
}
