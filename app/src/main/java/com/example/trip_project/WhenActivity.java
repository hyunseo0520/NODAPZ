package com.example.trip_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WhenActivity extends AppCompatActivity {
    //Context로 다음 액티비티에서 정보 사용
    public static Context DayContext;

    RadioButton rdoStartCal, rdoEndCal;
    DatePicker dPicker1, dPicker2;
    TextView tvYear1, tvMonth1, tvDay1,tvYear2, tvMonth2, tvDay2;
    Button btnDayOk;
    Button btnToTable;
    Button btnWho; //다음 액티비티로 넘어가는 버튼

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when);
        setTitle("여행 기간을 입력하세요!");

        //컨텍스트 설정
        DayContext = this;

        // 라디오버튼 2개
        rdoStartCal = (RadioButton) findViewById(R.id.rdoStartCal);
        rdoEndCal = (RadioButton) findViewById(R.id.rdoEndCal);

        // FrameLayout의 2개 위젯
        dPicker1 = (DatePicker) findViewById(R.id.datePicker1);
        dPicker2 = (DatePicker) findViewById(R.id.datePicker2);

        // 텍스트뷰 중에서 연,월,일,시,분 숫자
        tvYear1 = (TextView) findViewById(R.id.tvYear1);
        tvMonth1 = (TextView) findViewById(R.id.tvMonth1);
        tvDay1 = (TextView) findViewById(R.id.tvDay1);
        tvYear2 = (TextView) findViewById(R.id.tvYear2);
        tvMonth2 = (TextView) findViewById(R.id.tvMonth2);
        tvDay2 = (TextView) findViewById(R.id.tvDay2);


        dPicker1.setVisibility(View.INVISIBLE);
        dPicker2.setVisibility(View.INVISIBLE);

        rdoStartCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dPicker2.setVisibility(View.INVISIBLE);
                dPicker1.setVisibility(View.VISIBLE);
            }
        });

        rdoEndCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dPicker2.setVisibility(View.VISIBLE);
                dPicker1.setVisibility(View.INVISIBLE);
            }
        });

        btnDayOk = (Button)findViewById(R.id.BtnDayOk);
        btnDayOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYear1.setText(Integer.toString(dPicker1.getYear()));
                tvMonth1.setText(Integer.toString(1 + dPicker1.getMonth()));
                tvDay1.setText(Integer.toString(dPicker1.getDayOfMonth()));

                tvYear2.setText(Integer.toString(dPicker2.getYear()));
                tvMonth2.setText(Integer.toString(1 + dPicker2.getMonth()));
                tvDay2.setText(Integer.toString(dPicker2.getDayOfMonth()));
            }
        });

        btnWho = (Button) findViewById(R.id.button_when);
        btnWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음 액티비티로 가는 것
                //Intent
                Intent intent = new Intent(WhenActivity.this, WhoActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });
    }
}
