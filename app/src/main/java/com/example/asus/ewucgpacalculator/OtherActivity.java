package com.example.asus.ewucgpacalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class OtherActivity extends AppCompatActivity {

    TextView courseNumber[] = new TextView[6];
    EditText courseField1[] = new EditText[6];
    EditText courseField2[] = new EditText[6];
    Button submitButton ;
    TextView resultView;
    LinearLayout linear ;
    int number  ;
    String courseName;
    Double result = 0.0 , totalCredit = 0.0 , credit = 0.0 , finalResult ;
    int flag = 0 ;
    String error = "" , resultOutput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        resultView = new TextView(this);
        number = Integer.parseInt(getIntent().getStringExtra("courseTotal"));
        linear = findViewById(R.id.mainLayout);
        for(int l=1; l<=number; l++) {
            //TextView Create
          courseNumber[l] = new TextView(this);
          courseName = "Course ".concat(String.valueOf(l));
          courseNumber[l].setText(courseName);
          courseNumber[l].setTextSize(16);
          courseNumber[l].setPadding(0,30,0,0);
          courseNumber[l].setTextColor(Color.parseColor("#ce3232"));
          linear.addView(courseNumber[l]);
          //Edit Text Create
          courseField1[l] = new EditText(this);
          courseField1[l].setHint("Credit (3 or 4)");
          courseField1[l].setInputType(InputType.TYPE_CLASS_NUMBER);
          courseField1[l].setFilters(new InputFilter[]{new InputFilterMinMax("3", "4")});
          linear.addView(courseField1[l]);
          courseField2[l] = new EditText(this);
          courseField2[l].setHint("Grade What You Get (0 to 4)");
          courseField2[l].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
          courseField2[l].setFilters(new InputFilter[]{new InputFilterMinMax("0.0", "4.0")});
          linear.addView(courseField2[l]);
        }
        //Button Create
        buttonCreate();

        //After button click
        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Editext field initialize
                resultView.setText("");
                //Output text design
                resultView.setTextColor(Color.parseColor("#504f4f"));
                resultView.setTextSize(20);
                resultView.setPadding(0,50,0,100);

                for(int l=1; l<=number; l++) {
                    if(courseField1[l].getText().length() == 0 || courseField2[l].getText().length() == 0){
                        Toast.makeText(getApplicationContext(), "Input can not be empty!" ,
                                Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }else{
                        double inputFieldTwo = Double.parseDouble(String.valueOf(courseField2[l].getText()));
                        if(inputFieldTwo == 0 || inputFieldTwo == 1 || inputFieldTwo == 1.3 || inputFieldTwo == 1.7 ||inputFieldTwo == 2 ||inputFieldTwo == 2.3 ||
                                inputFieldTwo == 2.7 ||
                                inputFieldTwo == 3 ||
                                inputFieldTwo == 3.3 ||
                                inputFieldTwo == 3.7 ||
                                inputFieldTwo == 4 ){

                            credit = Double.parseDouble(String.valueOf(courseField1[l].getText()));
                            totalCredit = totalCredit + credit ;
                            result = result + (credit * Double.parseDouble(String.valueOf(courseField2[l].getText()))) ;
                        }
                        else{
                            flag = 2 ;
                            error = "Input problems for grade field. Example: for 0,1,2 etc enter exactly 0,1,2 etc. For 1.3, 2.7, 3.7 etc enter exactly" +
                                            " 1.3, 2.7, 3.7 etc." ;
                        }
                    }
                }

                //Calculation
                if(flag==0){
                    finalResult = result / totalCredit ;
                    DecimalFormat df = new DecimalFormat("#.##");
                    finalResult = Double.valueOf(df.format(finalResult));
                    resultOutput = "Your Gpa for this semester : ".concat(finalResult.toString());

                    for(int l=1; l<=number; l++) {
                        courseField1[l].setText("");
                        courseField2[l].setText("");
                    }
                }
                else{
                    initialize();
                }

                // Error or Result Print
                if(flag==0){
                    resultView.setText(resultOutput);
                    linear.addView(resultView);
                }else if(flag==2){
                    resultView.setText(error);
                    linear.addView(resultView);
                }


            }
        });

    }

    public void initialize(){
        for(int l=1; l<=number; l++) {
            courseField1[l].setText("");
            courseField2[l].setText("");
        }
    }

    public void buttonCreate(){
        submitButton = new Button(this);
        submitButton.setText("Calculate");
        submitButton.setTextColor(Color.WHITE);
        submitButton.setBackgroundColor(Color.parseColor("#D32F2F"));
        linear.addView(submitButton);
    }


}
