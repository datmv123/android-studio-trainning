package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    enum Operator {
        ADD, SUBTRACT, MULTIPLE, DIVIDE
    }

    private static String CHECK_INPUT_AGAIN = "Please check your input again.";
    private static String CAN_NOT_DEVIDE_BY_0 = "Cannot devide by 0";

    TextView tvNumber1 = null;
    TextView tvNumber2 = null;
    TextView result = null;

    RadioButton radioButtonAdd = null;
    RadioButton radioButtonSubtract = null;
    RadioButton radioButtonMultiple = null;
    RadioButton radioButtonDevide = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber1 = findViewById(R.id.number1);
        tvNumber2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);

        Button add = findViewById(R.id.buttonAdd);
        Button subtract = findViewById(R.id.buttonSubtract);
        Button multiple = findViewById(R.id.buttonMultiple);
        Button divide = findViewById(R.id.buttonDivide);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        radioButtonAdd = findViewById(R.id.radioButtonAdd);
        radioButtonSubtract = findViewById(R.id.radioButtonSubtract);
        radioButtonMultiple = findViewById(R.id.radioButtonMultiple);
        radioButtonDevide = findViewById(R.id.radioButtonDevide);

        buttonCalculate.setOnClickListener(t -> doCalculate(getOperator()));

        add.setOnClickListener(t -> doCalculate(Operator.ADD));
        subtract.setOnClickListener(t -> doCalculate(Operator.SUBTRACT));
        multiple.setOnClickListener(t -> doCalculate(Operator.MULTIPLE));
        divide.setOnClickListener(t -> doCalculate(Operator.DIVIDE));

    }

    private Operator getOperator() {
        if (radioButtonAdd.isChecked() == true) {
            return Operator.ADD;
        } else if (radioButtonSubtract.isChecked() == true) {
            return Operator.SUBTRACT;
        } else if (radioButtonMultiple.isChecked() == true) {
            return Operator.MULTIPLE;
        } else {
            return Operator.DIVIDE;
        }
    }

    private void doCalculate(Operator operator) {
        try {
            double number1 = Double.parseDouble(tvNumber1.getText().toString());
            double number2 = Double.parseDouble(tvNumber2.getText().toString());
            String value = "";
            switch (operator) {
                case ADD:
                    value = String.valueOf(number1 + number2);
                    break;
                case SUBTRACT:
                    value = String.valueOf(number1 - number2);
                    break;
                case MULTIPLE:
                    value = String.valueOf(number1 * number2);
                    break;
                case DIVIDE:
                    if (number2 == 0) {
                        result.setText(CAN_NOT_DEVIDE_BY_0);
                        return;
                    }
                    value = Double.toString(number1 / number2);
                    break;
            }
            result.setText(value);
        } catch (Exception e) {
            result.setText(CHECK_INPUT_AGAIN);
        }
    }


}
