package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button btn_add, btn_view;
    EditText et_name, et_age;
    ListView Iv_CustomerList;
    ArrayAdapter studentArrayAdapter;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_viewAll);
        et_age=findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        Iv_CustomerList = findViewById(R.id.Iv_cudtomerList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        ShowStudentsOnListView(dataBaseHelper);




        btn_view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                ShowStudentsOnListView(dataBaseHelper);


            }
        });

        btn_add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentMod studentMod;
                try {
                    studentMod = new StudentMod(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()));
                    Toast.makeText(MainActivity.this, studentMod.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Valid input", Toast.LENGTH_SHORT).show();
                    studentMod = new StudentMod(-1, "ERROR", 0);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean b = dataBaseHelper.addOne(studentMod);
                Toast.makeText(MainActivity.this, "SUCCESS= "+ b, Toast.LENGTH_SHORT).show();

                ShowStudentsOnListView(dataBaseHelper);

            }
        });




    }

    private void ShowStudentsOnListView(DataBaseHelper dataBaseHelper) {
        studentArrayAdapter = new ArrayAdapter<StudentMod>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
       Iv_CustomerList.setAdapter(studentArrayAdapter);
    }
}