package com.example.trip_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity {
    public static Context InputContext;
    private EditText editText_name, editText_phone;
    private Button button_submit, button_back;
    private ArrayList<Person> personList;
    private ArrayAdapter<Person> adapter;
    private int count = 0;
    private int maxNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        InputContext = this;

        maxNumber = getIntent().getIntExtra("number", 0);

        editText_name = findViewById(R.id.editText_name);
        editText_phone = findViewById(R.id.editText_phone);
        button_submit = findViewById(R.id.button_submit);
        button_back = findViewById(R.id.button_back);

        personList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < maxNumber) {
                    String name = editText_name.getText().toString();
                    String phone = editText_phone.getText().toString();

                    if (name.isEmpty() || phone.isEmpty()) {
                        Toast.makeText(InputActivity.this, "이름과 전화번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return; // 입력이 비어있을 경우 함수 종료
                    }

                    Person person = new Person(name, phone);
                    personList.add(person);

                    adapter.notifyDataSetChanged();

                    editText_name.setText("");
                    editText_phone.setText("");

                    count++;
                } else {
                    Toast.makeText(InputActivity.this, "입력 가능한 횟수를 초과하였습니다.", Toast.LENGTH_SHORT).show();
                }

                if (count == maxNumber) {
                    Intent intent = new Intent();
                    intent.putExtra("inputCount", count);
                    setResult(WhoActivity.RESULT_CODE_INPUT, intent); // WhoActivity로 동승자 정보 개수 전달
                    finish();
                }
            }
        });


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = personList.get(position);

                Intent intent = new Intent(InputActivity.this, EditActivity.class);
                intent.putExtra("name", person.getName());
                intent.putExtra("phone", person.getPhone());
                intent.putExtra("position", position);

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            int position = data.getIntExtra("position", -1);

            Person person = personList.get(position);
            person.setName(name);
            person.setPhone(phone);

            adapter.notifyDataSetChanged();
        }
    }
}
