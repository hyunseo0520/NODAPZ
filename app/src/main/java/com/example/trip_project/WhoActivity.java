package com.example.trip_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WhoActivity extends AppCompatActivity {
    private int number = 0;
    private TextView textView_number;
    private Button button_increase, button_decrease;
    private ImageView imageView_person;
    Button btnWho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        textView_number = findViewById(R.id.textView_number);
        button_increase = findViewById(R.id.button_increase);
        button_decrease = findViewById(R.id.button_decrease);
        imageView_person = findViewById(R.id.imageView_person);

        button_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < 6) {
                    number++;
                    imageView_person.setImageResource(getResources().getIdentifier("person" + number, "drawable", getPackageName()));
                    textView_number.setText(String.valueOf(number)+"명");
                }
            }
        });

        button_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 0) {
                    number--;
                    imageView_person.setImageResource(getResources().getIdentifier("person" + number, "drawable", getPackageName()));
                    textView_number.setText(String.valueOf(number)+"명");
                }
            }
        });

        // 액티비티가 시작될 때 인원 수를 0명으로 설정하고, person0.png 이미지를 보여줍니다.
        imageView_person.setImageResource(getResources().getIdentifier("person0", "drawable", getPackageName()));
        textView_number.setText(String.valueOf(number)+"명");



        btnWho = (Button) findViewById(R.id.button_who);
        btnWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음 액티비티로 가는 것
                //Intent
                Intent intent = new Intent(WhoActivity.this, WhatActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });
    }
}
