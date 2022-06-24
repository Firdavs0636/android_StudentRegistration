package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, family_name_input, age_input;
    Button update_button, delete_button;

    String id, name, family_name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        family_name_input = findViewById(R.id.family_name_input2);
        age_input = findViewById(R.id.age_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button1);

        //first we call this
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        ab.setTitle(name);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //then we update
                MyDatatbasesHelper myDB = new MyDatatbasesHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                family_name = family_name_input.getText().toString().trim();
                age = age_input.getText().toString().trim();
                myDB.updateData(id, name, family_name, age);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
            getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //getting intent data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("title");
            family_name = getIntent().getStringExtra("author");
            age = getIntent().getStringExtra("pages");
            //setting intent data
            name_input.setText(name);
            family_name_input.setText(family_name);
            age_input.setText(age);
            Log.d("stev", name+" "+family_name+" "+age);


        }else {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatatbasesHelper myDB = new MyDatatbasesHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}