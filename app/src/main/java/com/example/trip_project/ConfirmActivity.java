package com.example.trip_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {

    public static Context InputContext;
    public static Context WhenContext;
    public static Context WhereContext;
    public static Context WhoContext;
    private Button btnWhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        TextView inputView = findViewById(R.id.inputView);
        TextView whenView = findViewById(R.id.whenView);
        TextView whereView = findViewById(R.id.whereView);
        TextView whoView = findViewById(R.id.whoView);

        if(InputContext != null) {
            inputView.setText(InputContext.toString());
        } else {
            inputView.setText("InputContext is null");
        }

        if(WhenContext != null) {
            whenView.setText(WhenContext.toString());
        } else {
            whenView.setText("WhenContext is null");
        }

        if(WhereContext != null) {
            whereView.setText(WhereContext.toString());
        } else {
            whereView.setText("WhereContext is null");
        }

        if(WhoContext != null) {
            whoView.setText(WhoContext.toString());
        } else {
            whoView.setText("WhoContext is null");
        }

        btnWhat = findViewById(R.id.button_confirm);
        btnWhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음 액티비티로 가는 것
                //Intent
                Intent intent = new Intent(ConfirmActivity.this, MainActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });

    }
}
