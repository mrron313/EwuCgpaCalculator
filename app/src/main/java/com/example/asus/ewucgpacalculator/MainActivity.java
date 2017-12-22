package com.example.asus.ewucgpacalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        private EditText courseTotalEdit;
        private Button clickButton2;
        int number;
        String courseNumber;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickButton2 = (Button) findViewById(R.id.clickButton);
        courseTotalEdit = (EditText) findViewById(R.id.courseTotal);
        courseTotalEdit.setFilters(new InputFilter[]{new InputFilterMinMax("2", "5")});
        clickButton2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                 if(courseTotalEdit.getText().length() == 0){
                        Toast.makeText(getApplicationContext(), "Enter number between 2 and 5 please ." ,
                                Toast.LENGTH_SHORT).show();
                }else {
                        courseNumber = courseTotalEdit.getText().toString();
                        number = Integer.parseInt(courseNumber);
                        if(number>5 || number <1 ){
                                Toast.makeText(getApplicationContext(), "Enter number between 2 and 5 please ." ,
                                        Toast.LENGTH_SHORT).show();
                                courseTotalEdit.setText("");
                        }else if(number<=5 && number>=1){
                                Intent intent=new Intent(MainActivity.this,OtherActivity.class);
                                intent.putExtra("courseTotal", courseNumber);
                                startActivity(intent);
                        }
                        else{
                                Toast.makeText(getApplicationContext(), "Enter number between 1 and 5 please ." ,
                                        Toast.LENGTH_SHORT).show();
                        }
                }

                }
        });

        }
}