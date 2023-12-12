package com.example.trip_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WhoActivity extends AppCompatActivity {
    public static Context WhoContext;
    public static final int RESULT_CODE_INPUT = 2;
    private int number = 0;
    private TextView textView_number;
    private Button button_increase, button_decrease;
    private ImageView imageView_person;
    private Button btnWho;
    private Button button_next;

    private static final int REQUEST_CODE_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        WhoContext = this;

        textView_number = findViewById(R.id.textView_number);
        button_increase = findViewById(R.id.button_increase);
        button_decrease = findViewById(R.id.button_decrease);
        imageView_person = findViewById(R.id.imageView_person);
        btnWho = findViewById(R.id.button_who);
        button_next = findViewById(R.id.button_next);
        button_next.setEnabled(false); // 초기에 next 버튼 비활성화

        btnWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhoActivity.this, InputActivity.class);
                intent.putExtra("number", number);
                startActivityForResult(intent, REQUEST_CODE_INPUT); // InputActivity 시작
            }
        });

        button_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < 6) {
                    number++;
                    imageView_person.setImageResource(getResources().getIdentifier("person" + (number == 6 ? "6" : number), "drawable", getPackageName()));
                    textView_number.setText(number == 6 ? "6명 이상" : String.valueOf(number) + "명");
                }
                updateNextButton(); // 버튼 상태 업데이트
            }
        });

        button_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 0) {
                    number--;
                    imageView_person.setImageResource(getResources().getIdentifier("person" + number, "drawable", getPackageName()));
                    textView_number.setText(number == 0 ? "0명" : String.valueOf(number) + "명");
                }
                updateNextButton(); // 버튼 상태 업데이트
            }
        });

        imageView_person.setImageResource(getResources().getIdentifier("person0", "drawable", getPackageName()));
        textView_number.setText(String.valueOf(number) + "명");

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhoActivity.this, ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateNextButton() {
        if (number > 0) {
            button_next.setEnabled(true); // 여행자 인원이 1명 이상이면 next 버튼 활성화
        } else {
            button_next.setEnabled(false); // 여행자 인원이 0명이면 next 버튼 비활성화
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INPUT && resultCode == RESULT_CODE_INPUT) {
            if (data != null) {
                int inputCount = data.getIntExtra("inputCount", 0);
                if (inputCount < number) {
                    button_next.setEnabled(false); // 입력된 동승자 정보의 개수가 선택한 인원보다 작을 경우 next 버튼 비활성화
                }
            }
        }
    }
}