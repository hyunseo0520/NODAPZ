package com.example.trip_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WhoActivity extends AppCompatActivity {
    private int number = 0;
    private TextView textView_number;
    private Button button_increase, button_decrease;
    private ImageView imageView_person;
    Button btnWho;
    private CheckBox checkbox_family, checkbox_friends, checkbox_colleague;
    private TextView textView_categories;
    private EditText editText_newCategory;
    private Button button_addCategory;
    private LinearLayout layout_categories;
    private ArrayList<CheckBox> categoryCheckboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        textView_number = findViewById(R.id.textView_number);
        button_increase = findViewById(R.id.button_increase);
        button_decrease = findViewById(R.id.button_decrease);
        imageView_person = findViewById(R.id.imageView_person);

        checkbox_family = findViewById(R.id.checkbox_family);
        checkbox_friends = findViewById(R.id.checkbox_friends);
        checkbox_colleague = findViewById(R.id.checkbox_colleague);

        textView_categories = findViewById(R.id.textView_categories);
        editText_newCategory = findViewById(R.id.editText_newCategory);
        button_addCategory = findViewById(R.id.button_addCategory);
        layout_categories = findViewById(R.id.layout_categories);

        categoryCheckboxes = new ArrayList<>();
        categoryCheckboxes.add(checkbox_family);
        categoryCheckboxes.add(checkbox_friends);
        categoryCheckboxes.add(checkbox_colleague);

        button_addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCategory(editText_newCategory.getText().toString());
                editText_newCategory.setText("");
            }
        });
        View.OnClickListener checkboxListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategories();
            }
        };

        checkbox_family.setOnClickListener(checkboxListener);
        checkbox_friends.setOnClickListener(checkboxListener);
        checkbox_colleague.setOnClickListener(checkboxListener);

        button_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < 6) {
                    number++;
                    imageView_person.setImageResource(getResources().getIdentifier("person" + (number == 6 ? "6" : number), "drawable", getPackageName()));
                    textView_number.setText(number == 6 ? "6명 이상" : String.valueOf(number) + "명");
                }
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
    private void addNewCategory(String category) {
        CheckBox newCheckbox = new CheckBox(this);
        newCheckbox.setText(category);
        newCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategories();
            }
        });

        layout_categories.addView(newCheckbox);
        categoryCheckboxes.add(newCheckbox);
    }

    private void updateCategories() {
        StringBuilder categories = new StringBuilder();

        for (CheckBox checkbox : categoryCheckboxes) {
            if (checkbox.isChecked()) {
                categories.append(checkbox.getText().toString()).append(" ");
            }
        }

        TextView textView_categories = findViewById(R.id.textView_categories);
        textView_categories.setText(categories.toString());
    }
}
