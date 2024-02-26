package com.techmania.calculatorwithjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9
            ,btnAC,btnDel,btnPlus,btnMinus,btnDivide,btnMulti,btnEquals,btnDot;

    private TextView textViewResult,textViewHistory;

    String number = null;

    double firstNumber = 0.0;
    double lastNumber = 0.0;

    String status = null;
    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history = "";
    String currentResult = "";

    boolean dotControl = true;
    boolean buttonEqualsControl = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btnZero);
        btn1 = findViewById(R.id.btnOne);
        btn2 = findViewById(R.id.btnTwo);
        btn3 = findViewById(R.id.btnThree);
        btn4 = findViewById(R.id.btnFour);
        btn5 = findViewById(R.id.btnFive);
        btn6 = findViewById(R.id.btnSix);
        btn7 = findViewById(R.id.btnSeven);
        btn8 = findViewById(R.id.btnEight);
        btn9 = findViewById(R.id.btnNine);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMulti = findViewById(R.id.btnMulti);

        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnDot = findViewById(R.id.btnDot);
        btnEquals = findViewById(R.id.btnEqual);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        textViewResult.setText("0");

        btn0.setOnClickListener(v -> {
            onNumberClicked("0");
        });
        btn1.setOnClickListener(v -> {
            onNumberClicked("1");
        });
        btn2.setOnClickListener(v -> {
            onNumberClicked("2");
        });
        btn3.setOnClickListener(v -> {
            onNumberClicked("3");
        });
        btn4.setOnClickListener(v -> {
            onNumberClicked("4");
        });
        btn5.setOnClickListener(v -> {
            onNumberClicked("5");
        });
        btn6.setOnClickListener(v -> {
            onNumberClicked("6");
        });
        btn7.setOnClickListener(v -> {
            onNumberClicked("7");
        });
        btn8.setOnClickListener(v -> {
            onNumberClicked("8");
        });
        btn9.setOnClickListener(v -> {
            onNumberClicked("9");
        });
        btnDivide.setOnClickListener(v -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history.concat(currentResult).concat("/"));

            if (operator) {
                if (status != null){
                    switch (status) {
                        case "multiplication":
                            multiply();
                            break;
                        case "division":
                            divide();
                            break;
                        case "subtraction":
                            minus();
                            break;
                        case "addition":
                            plus();
                            break;
                    }
                }else {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
            }

            status = "division";
            operator = false;
            number = null;
            dotControl = true;


        });
        btnMulti.setOnClickListener(v -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history.concat(currentResult).concat("*"));

            if (operator) {
                if (status != null){
                    switch (status) {
                        case "multiplication":
                            multiply();
                            break;
                        case "division":
                            divide();
                            break;
                        case "subtraction":
                            minus();
                            break;
                        case "addition":
                            plus();
                            break;
                    }
                }else {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
            }

            status = "multiplication";
            operator = false;
            number = null;
            dotControl = true;


        });
        btnMinus.setOnClickListener(v -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history.concat(currentResult).concat("-"));

            if (operator) {
                if (status != null){
                    switch (status) {
                        case "multiplication":
                            multiply();
                            break;
                        case "division":
                            divide();
                            break;
                        case "subtraction":
                            minus();
                            break;
                        case "addition":
                            plus();
                            break;
                    }
                }else {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
            }

            status = "subtraction";
            operator = false;
            number = null;
            dotControl = true;


        });
        btnPlus.setOnClickListener(v -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history.concat(currentResult).concat("+"));

            if (operator) {
                if (status != null){
                    switch (status) {
                        case "multiplication":
                            multiply();
                            break;
                        case "division":
                            divide();
                            break;
                        case "subtraction":
                            minus();
                            break;
                        case "addition":
                            plus();
                            break;
                    }
                }else {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
            }

            status = "addition";
            operator = false;
            number = null;
            dotControl = true;


        });
        btnDot.setOnClickListener(v -> {

            if (dotControl) {
                if (number == null) {
                    number = "0.";
                } else if (buttonEqualsControl) {
                    if (textViewResult.getText().toString().contains(".")) {
                        textViewResult.getText().toString();
                    } else {
                        textViewResult.setText(textViewResult.getText().toString() + ".");
                    }
                } else {
                    number += ".";
                }

                textViewResult.setText(number);
            }

            dotControl = false;


        });
        btnEquals.setOnClickListener(v -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();

            if (operator) {
                switch (status) {
                    case "multiplication":
                        multiply();
                        break;
                    case "division":
                        divide();
                        break;
                    case "subtraction":
                        minus();
                        break;
                    case "addition":
                        plus();
                        break;
                    default:
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                        break;
                }

                textViewHistory.setText(history + currentResult + "=" + textViewResult.getText().toString());
            }

            operator = false;
            dotControl = true;
            buttonEqualsControl = true;


        });
        btnAC.setOnClickListener(v -> {
            onButtonACClicked();
        });
        btnDel.setOnClickListener(v -> {

            if (number != null) {
                if (number.length() == 1) {
                    onButtonACClicked();
                } else {
                    number = number.substring(0, number.length() - 1);
                    textViewResult.setText(number);
                    dotControl = !number.contains(".");
                }
            }


        });

    }

    void onNumberClicked(String clickedNumber) {
        if (number == null) {
            number = clickedNumber;
        } else if (buttonEqualsControl) {
            number = dotControl ? clickedNumber : textViewResult.getText().toString() + clickedNumber;
            firstNumber = Double.parseDouble(number);
            lastNumber = 0.0;
            status = null;
            textViewHistory.setText("");
        } else {
            number += clickedNumber;
        }

        textViewResult.setText(number);

        operator = true;
        buttonEqualsControl = false;
    }

    void onButtonACClicked() {
        number = null;
        status = null;
        textViewResult.setText("0");
        textViewHistory.setText("");
        firstNumber = 0.0;
        lastNumber = 0.0;
        dotControl = true;
        buttonEqualsControl = false;
    }

    void plus() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber += lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));
    }

    void minus() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber -= lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));
    }

    void multiply() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber *= lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));
    }

    void divide() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());

        if (lastNumber == 0.0) {
            Toast.makeText(getApplicationContext(), "The divisor cannot be zero", Toast.LENGTH_LONG).show();
        } else {
            firstNumber /= lastNumber;
            textViewResult.setText(myFormatter.format(firstNumber));
        }
    }



}