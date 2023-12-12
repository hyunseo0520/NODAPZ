package com.example.trip_project.hotel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        }

        // 버튼 클릭 이벤트 처리
        Button startBookingButton = findViewById(R.id.button);
        startBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookingIntent = new Intent(DetailsActivity.this, WebActivity.class);
                startActivity(bookingIntent);
            }
        });
    }
}
