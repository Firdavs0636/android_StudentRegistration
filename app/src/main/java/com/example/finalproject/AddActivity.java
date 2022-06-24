package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddActivity extends AppCompatActivity {

    EditText name_input, family_name_input, age_input;
    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        family_name_input = findViewById(R.id.family_name_input);
        age_input = findViewById(R.id.age_input);
        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatatbasesHelper myDB = new MyDatatbasesHelper(AddActivity.this);
                myDB.register(name_input.getText().toString().trim(),
                        family_name_input.getText().toString().trim(),
                        Integer.valueOf(age_input.getText().toString().trim()));
            }
        });

    }
}