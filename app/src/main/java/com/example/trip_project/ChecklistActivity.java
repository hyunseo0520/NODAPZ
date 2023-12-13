package com.example.trip_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChecklistActivity extends AppCompatActivity {
    private EditText editTextTravelDate;
    private Spinner spinnerTravelLocation;
    private Spinner spinnerTravelType;
    private ListView listViewRecommendations;
    private Button buttonAddItem, btnHome;
    private List<String> travelTypes;
    private boolean[] checkedTravelTypes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        listViewRecommendations = findViewById(R.id.listViewRecommendations);
        buttonAddItem = findViewById(R.id.buttonAddItem);

        travelTypes = Arrays.asList("국내여행", "해외여행", "액티비티", "낭만", "전자기기", "세면용품", "기타", "우정");
        checkedTravelTypes = new boolean[travelTypes.size()];

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChecklistActivity.this);
                builder.setTitle("Choose travel types");

                builder.setMultiChoiceItems(travelTypes.toArray(new String[0]), checkedTravelTypes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedTravelTypes[which] = isChecked;
                            }
                        });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showRecommendations();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        btnHome = findViewById(R.id.button_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음 액티비티로 가는 것
                //Intent
                Intent intent = new Intent(ChecklistActivity.this, MainActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });

    }

    private void showRecommendations() {
        List<String> recommendations = new ArrayList<>();

        for (int i = 0; i < travelTypes.size(); i++) {
            if (checkedTravelTypes[i]) {
                if (travelTypes.get(i).equals("국내여행")) {
                    recommendations.addAll(Arrays.asList("국내신분증", "국내운전면허증", "여행지 결정", "숙소", "지갑"));
                } else if (travelTypes.get(i).equals("해외여행")) {
                    recommendations.addAll(Arrays.asList("비자", "세금(택스) 관련 서류", "국제운전면허증", "여권", "바우처", "숙소", "지갑", "환전한 돈"));
                } else if (travelTypes.get(i).equals("액티비티")) {
                    recommendations.addAll(Arrays.asList("운동화", "수건", "에너지드링크", "액션캠", "수건", "관절보호대"));
                } else if (travelTypes.get(i).equals("낭만")) {
                    recommendations.addAll(Arrays.asList("카메라", "필름", "녹음기", "스케치북", "이어폰", "선글라스"));
                } else if (travelTypes.get(i).equals("전자기기")) {
                    recommendations.addAll(Arrays.asList("노트북", "태블릿PC", "노트북 충전기", "태블릿PC 충전기"));
                } else if (travelTypes.get(i).equals("세면용품")) {
                    recommendations.addAll(Arrays.asList("클렌징폼", "치약", "칫솔", "에센스", "로션", "화장솜"));
                } else if (travelTypes.get(i).equals("우정")) {
                    recommendations.addAll(Arrays.asList("보드게임", "편지지", "불멍가루", "필름카메라", "간식", "친구"));
                } else if (travelTypes.get(i).equals("바다")) {
                    recommendations.addAll(Arrays.asList("샤워용품", "물안경", "여분의 옷", "선글라스", "썬크림", "파라솔", "돗자리"));
                } else if (travelTypes.get(i).equals("산")) {
                    recommendations.addAll(Arrays.asList("챙모자", "선글라스", "썬크림", "지팡이", "벌레퇴치제", "백팩", "물", "상비약"));
                } else if (travelTypes.get(i).equals("기타")) {
                    recommendations.addAll(Arrays.asList("압축용팩", "비닐봉투", "핸드크림", "가글", "미스트", "백팩"));
                }

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, recommendations) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
                CheckBox checkBox = convertView.findViewById(R.id.tripcheckBox);
                checkBox.setText(getItem(position));

                SharedPreferences sharedPreferences = getSharedPreferences("your_preferences_name", MODE_PRIVATE);
                boolean checkState = sharedPreferences.getBoolean(getItem(position), false);
                checkBox.setChecked(checkState);

                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    SharedPreferences sharedPreferences1 = getSharedPreferences("your_preferences_name", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean(getItem(position), isChecked);
                    editor.apply();

                    if (isChecked) {
                        checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        checkBox.setPaintFlags(checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                });

                return convertView;
            }
        };

        listViewRecommendations.setAdapter(adapter);
    }
}