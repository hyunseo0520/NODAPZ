package com.example.trip_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhereActivity extends AppCompatActivity {
    public static Context WhereContext;

    Button btnWhere, btnSearch;
    Spinner countrySpinner;
    EditText etSearch;

    public class CustomArrayAdapter extends ArrayAdapter<String> {

        private int mSelectedIndex = -1;

        public CustomArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            tv.setBackgroundColor(position == mSelectedIndex ? Color.parseColor("#F6CED8") : Color.WHITE);
            return view;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView tv = (TextView) view;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30); // 선택된 아이템의 글자 크기를 30dp로 설정
            tv.setGravity(Gravity.CENTER); // 텍스트 가운데 정렬 설정
            return view;
        }

        public void setSelection(int position) {
            mSelectedIndex = position;
            notifyDataSetChanged();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where);
        setTitle("여행 장소를 입력하세요!");

        WhereContext = this;

        setupCountrySpinner();
        setupSearchButton();
        setupWhereButton();
    }

    private void setupCountrySpinner() {
        countrySpinner = (Spinner) findViewById(R.id.spinner_country);
        final List<String> countries = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.country_names)));
        countries.add(0, "선택하세요");
        final CustomArrayAdapter adapter = new CustomArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    adapter.setSelection(position);

                    // 선택된 국가를 SharedPreferences에 저장
                    SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("selectedCountry", countries.get(position));
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupSearchButton() {
        etSearch = (EditText) findViewById(R.id.et_search);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = etSearch.getText().toString();
                int position = ((CustomArrayAdapter) countrySpinner.getAdapter()).getPosition(search);
                if (position != -1) {
                    countrySpinner.setSelection(position);
                    ((CustomArrayAdapter) countrySpinner.getAdapter()).setSelection(position);

                    // 도시 이름을 SharedPreferences에 저장
                    SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("selectedCity", search);
                    editor.apply();
                } else {
                    Toast.makeText(WhereActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupWhereButton() {
        btnWhere = (Button) findViewById(R.id.button_where);
        btnWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhereActivity.this, WhenActivity.class));
            }
        });
    }
}
