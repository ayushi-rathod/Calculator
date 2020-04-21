package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    String input1;
    String input2;
    String special_char;
    boolean isOperatorSelected = false;
    boolean newExpr = true;
    String operator = "";
    String resultExpr = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView cal_input = (TextView) findViewById(R.id.cal_input);
        final TextView cal_result = (TextView) findViewById(R.id.cal_result);
        Button button_one = (Button) findViewById(R.id.one_button);
        Button button_two = (Button) findViewById(R.id.two_button);
        Button button_three = (Button) findViewById(R.id.three_button);
        Button button_four = (Button) findViewById(R.id.four_button);
        Button button_five = (Button) findViewById(R.id.five_button);
        Button button_six = (Button) findViewById(R.id.six_button);
        Button button_seven = (Button) findViewById(R.id.seven_button);
        Button button_eight = (Button) findViewById(R.id.eight_button);
        Button button_nine = (Button) findViewById(R.id.nine_button);
        Button button_zero = (Button) findViewById(R.id.zero_button);
        Button button_plus = (Button) findViewById(R.id.plus_button);
        Button button_minus = (Button) findViewById(R.id.minus_button);
        Button button_multiply = (Button) findViewById(R.id.muliply_button);
        Button button_divide = (Button) findViewById(R.id.divide_button);
        Button button_del = (Button) findViewById(R.id.del_button);
        Button button_equal = (Button) findViewById(R.id.equal_button);
        Button button_clr = (Button) findViewById(R.id.clr_button);
        Button button_percentage = (Button) findViewById(R.id.percent_button);
        Button button_period = (Button) findViewById(R.id.period_button);

        View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!newExpr) {
                    cal_input.setText("");
                    cal_result.setText("");
                    newExpr = true;
                }
                switch (v.getId()) {
                    case R.id.one_button:
                        cal_input.append("1");
                        break;
                    case R.id.two_button:
                        cal_input.append("2");
                        break;
                    case R.id.three_button:
                        cal_input.append("3");
                        break;
                    case R.id.four_button:
                        cal_input.append("4");
                        break;
                    case R.id.five_button:
                        cal_input.append("5");
                        break;
                    case R.id.six_button:
                        cal_input.append("6");
                        break;
                    case R.id.seven_button:
                        cal_input.append("7");
                        break;
                    case R.id.eight_button:
                        cal_input.append("8");
                        break;
                    case R.id.nine_button:
                        cal_input.append("9");
                        break;
                    case R.id.zero_button:
                        cal_input.append("0");
                        break;
                    case R.id.period_button:
                        cal_input.append(".");
                        break;
                    case R.id.percent_button:
                        cal_input.append("%");
                        isOperatorSelected = true;
                        operator = "%";
                        break;
                    case R.id.plus_button:
                        if (!isOperatorSelected && !resultExpr.isEmpty()) {
                            cal_input.setText(resultExpr);
                        }
                        cal_input.append("+");
                        isOperatorSelected = true;
                        operator = "+";
                        break;
                    case R.id.minus_button:
                        if (operator.isEmpty() && !resultExpr.isEmpty()) {
                            cal_input.setText(resultExpr);
                            operator = "-";
                        } else if (!isOperatorSelected && (operator != "*" || operator != "/")) {
                            operator = "-";
                        } else if (operator == "+") {
                            cal_input.setText(cal_input.getText().toString().substring(0, cal_input.getText().length() - 1));
                            operator = "-";
                        } else if (cal_input.getText().charAt(0) != '-'){
                            operator = "-";
                        }
                        isOperatorSelected = true;
                        cal_input.append("-");
                        break;
                    case R.id.muliply_button:
                        if (!isOperatorSelected && !resultExpr.isEmpty()) {
                                cal_input.setText(resultExpr);
                        }
                        cal_input.append("*");
                        isOperatorSelected = true;
                        operator = "*";
                        break;
                    case R.id.divide_button:
                        if (!isOperatorSelected && !resultExpr.isEmpty()) {
                            cal_input.setText(resultExpr);
                        }
                        cal_input.append("/");
                        isOperatorSelected = true;
                        operator = "/";
                        break;

                }
            }
        };

        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = cal_input.getText().length();
                if (len-1 == 0) {
                    cal_input.setText("");
                    cal_result.setText("");
                }
                if (len-1 !=0) {
                    cal_input.setText(cal_input.getText().toString().substring(0, len - 1));
                    cal_result.setText("");
                }
            }
        });

        button_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal_input.setText("");
                cal_result.setText("");
            }
        });

        button_one.setOnClickListener(calculatorListener);
        button_two.setOnClickListener(calculatorListener);
        button_three.setOnClickListener(calculatorListener);
        button_four.setOnClickListener(calculatorListener);
        button_five.setOnClickListener(calculatorListener);
        button_six.setOnClickListener(calculatorListener);
        button_seven.setOnClickListener(calculatorListener);
        button_eight.setOnClickListener(calculatorListener);
        button_nine.setOnClickListener(calculatorListener);
        button_zero.setOnClickListener(calculatorListener);
        button_period.setOnClickListener(calculatorListener);
        button_percentage.setOnClickListener(calculatorListener);
        button_minus.setOnClickListener(calculatorListener);
        button_plus.setOnClickListener(calculatorListener);
        button_multiply.setOnClickListener(calculatorListener);
        button_divide.setOnClickListener(calculatorListener);

        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float result = 0;
                if (cal_input.getText().length() < 3 && isOperatorSelected || cal_input.getText().length() < 3 && !isOperatorSelected
                    || !isOperatorSelected) {
                    cal_result.setText("Not a valid input!");
                    isOperatorSelected = false;
                    newExpr =false;
                } else {
                    switch (operator) {
                        case "+":
                            input1 = cal_input.getText().toString().split("\\+")[0];
                            input2 = cal_input.getText().toString().split("\\+")[1];
                            result = (Float.parseFloat(input1) + Float.parseFloat(input2));
                            cal_result.setText("" + result + "");
                            break;
                        case "-":
                            if (cal_input.getText().toString().charAt(0) == '-') {
                                input1 = cal_input.getText().toString().substring(1, cal_input.length()-1).split("\\-")[0];
                                input1 = "-" + input1;

                            } else {
                                input1 = cal_input.getText().toString().split("\\-")[0];
                            }
                            input2 = cal_input.getText().toString().split("\\-")[1];
                            result = (Float.parseFloat(input1) - Float.parseFloat(input2));
                            cal_result.setText("" + result + "");
                            break;
                        case "*":
                            input1 = cal_input.getText().toString().split("\\*")[0];
                            input2 = cal_input.getText().toString().split("\\*")[1];
                            result = (Float.parseFloat(input1) * Float.parseFloat(input2));
                            cal_result.setText("" + result + "");
                            break;
                        case "/":
                            input1 = cal_input.getText().toString().split("\\/")[0];
                            input2 = cal_input.getText().toString().split("\\/")[1];
                            if (Float.parseFloat(input2) == 0) {
                                cal_result.setText("Can't divide by 0");
                            } else {
                                result = (Float.parseFloat(input1) / Float.parseFloat(input2));
                                cal_result.setText("" + result + "");
                            }
                            break;
                        case "%":
                            input1 = cal_input.getText().toString().split("\\%")[0];
                            input2 = cal_input.getText().toString().split("\\%")[1];
                            result = (Float.parseFloat(input1)/100)* (Float.parseFloat(input2));
                            cal_result.setText("" + result + "");
                    }
                    resultExpr = "" + result +"";
                    isOperatorSelected = false;
                    operator = "";
                    newExpr =false;
                }
            }
        });
    }
}