package com.example.trip_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    private EditText editText_name, editText_phone;
    private Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText_name = findViewById(R.id.editText_name);
        editText_phone = findViewById(R.id.editText_phone);
        button_submit = findViewById(R.id.button_submit);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        final int position = intent.getIntExtra("position", -1);

        editText_name.setText(name);
        editText_phone.setText(phone);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editText_name.getText().toString();
                String newPhone = editText_phone.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", newName);
                resultIntent.putExtra("phone", newPhone);
                resultIntent.putExtra("position", position);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
