package com.example.calculadorahp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado;

    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button num0;
    private Button punto;

    private Button mult;
    private Button div;
    private Button rest;
    private Button suma;
    private Button igual;

    private double op1;
    private double op2;

    private int op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initButtons();
    }

    private void initViews() {
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);
        num0 = (Button) findViewById(R.id.num0);
        punto = (Button) findViewById(R.id.punto);

        mult = (Button) findViewById(R.id.mult);
        div = (Button) findViewById(R.id.div);
        rest = (Button) findViewById(R.id.rest);
        suma = (Button) findViewById(R.id.suma);
        igual = (Button) findViewById(R.id.igual);
    }

    private void initButtons() {
        num1.setOnClickListener(v -> clickNum(num1));
        num2.setOnClickListener(v -> clickNum(num2));
        num3.setOnClickListener(v -> clickNum(num3));
        num4.setOnClickListener(v -> clickNum(num4));
        num5.setOnClickListener(v -> clickNum(num5));
        num6.setOnClickListener(v -> clickNum(num6));
        num7.setOnClickListener(v -> clickNum(num7));
        num8.setOnClickListener(v -> clickNum(num8));
        num9.setOnClickListener(v -> clickNum(num9));
        num0.setOnClickListener(v -> clickNum(num0));
        punto.setOnClickListener(v -> clickNum(punto));

        mult.setOnClickListener(v -> clickOp(mult));
        div.setOnClickListener(v -> clickOp(div));
        rest.setOnClickListener(v -> clickOp(rest));
        suma.setOnClickListener(v -> clickOp(suma));

        igual.setOnClickListener(v -> clickIgual());
    }

    private void clickIgual() {
        op2 = Double.parseDouble(tvResultado.getText().toString());
        if(op!=0){
            switch (op){
                case R.id.mult:
                    tvResultado.setText(Double.toString(op1*op2));
                    break;
                case R.id.rest:
                    tvResultado.setText(Double.toString(op1-op2));
                    break;
                case R.id.suma:
                    tvResultado.setText(Double.toString(op1+op2));
                    break;
                case R.id.div:
                    tvResultado.setText(Double.toString(op1/op2));
                    break;
                default:
                    break;
            }
        }
        op = 0;
    }

    private void clickOp(Button b) {
        op1 = Double.parseDouble(tvResultado.getText().toString());
        tvResultado.setText("");
        if(op==0) {
            switch (b.getId()) {
                case R.id.mult:
                    op = R.id.mult;
                    break;
                case R.id.rest:
                    op = R.id.rest;
                    break;
                case R.id.suma:
                    op = R.id.suma;
                    break;
                case R.id.div:
                    op = R.id.div;
                    break;
                default:
                    break;
            }
        }
    }

    private void clickNum(Button v) {
        String tv = tvResultado.getText().toString();

        switch (v.getId()){
            case R.id.num1:
                tv += 1;
                break;
            case R.id.num2:
                tv += 2;
                break;
            case R.id.num3:
                tv += 3;
                break;
            case R.id.num4:
                tv += 4;
                break;
            case R.id.num5:
                tv += 5;
                break;
            case R.id.num6:
                tv += 6;
                break;
            case R.id.num7:
                tv += 7;
                break;
            case R.id.num8:
                tv += 8;
                break;
            case R.id.num9:
                tv += 9;
                break;
            case R.id.num0:
                tv += 0;
                break;
            case R.id.punto:
                if(!tv.contains("."))
                    tv += ".";
                break;
            default:
                break;
        }
        tvResultado.setText(tv);
    }
}