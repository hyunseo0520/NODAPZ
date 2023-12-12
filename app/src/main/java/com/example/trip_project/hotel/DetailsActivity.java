package com.example.trip_project.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.trip_project.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Intent로 전달된 데이터 추출
        Intent intent = getIntent();
        if (intent != null) {
            String hotelName = intent.getStringExtra("hotelName");
            String countryName = intent.getStringExtra("countryName");
            String price = intent.getStringExtra("price");
            int imageResource = intent.getIntExtra("imageResource", 0);

            // 추출한 데이터를 사용하여 화면에 표시하거나 추가적인 작업 수행
            // 예: TextView 등에 데이터 설정
        }
    }
}
