package com.mayab.calidad.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.Math;
import java.text.DecimalFormat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0,btn_Add,btn_Sub,btn_Mul,btn_Div,btn_calc,btn_dec,btn_clear, btn_Sqrt, btn_negative;
    EditText ed1;
    String operation = "";
    Boolean repeat = false, clean = false;

    float first = Float.MAX_VALUE, second = Float.MAX_VALUE, result = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_Add = (Button) findViewById(R.id.btn_Add);
        btn_Div = (Button) findViewById(R.id.btn_Div);
        btn_Sub = (Button) findViewById(R.id.btn_Sub);
        btn_Mul = (Button) findViewById(R.id.btn_Mul);
        btn_calc = (Button) findViewById(R.id.btn_calc);
        btn_dec = (Button) findViewById(R.id.btn_dec);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_Sqrt = (Button) findViewById(R.id.btn_Sqrt);
        btn_negative = (Button) findViewById(R.id.btn_negative);
        ed1 = (EditText) findViewById(R.id.edText1);

        setButtons();

    }

    protected void sendToDisplay(String text){
        if (repeat) {
            ed1.setText("");
            repeat = false;
        }
        if (clean) {
            ed1.setText("0");
            clean = false;
        }
        ed1.setText(ed1.getText() + text);
    }

    protected void setOperation(String operation) {
        if (clean) {
            ed1.setText("0");
            clean = false;
        }
        this.operation = operation;
        if (ed1.getText() + "" == "")
            return;
        first = Float.parseFloat(ed1.getText() + "");
        ed1.setText("");
    }

    protected void doOperation(){
        if (first == Float.MAX_VALUE && second == Float.MAX_VALUE){ return; }
        if (ed1.getText() + "" != "" &&
            !repeat) {
            second = Float.parseFloat(ed1.getText() + "");
        } else if (second == Float.MAX_VALUE) {
            second = first;
        }
        switch (operation) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = division(first, second);
                if (result == Float.MAX_VALUE){
                    clean();
                    ed1.setText("Error");
                    clean = true;
                    return;
                }
                break;
        }
        ed1.setText(result + "");
        first = result;
        repeat = true;
    }

    protected void sqr(){
        if (ed1.getText() + "" == "")
            return;
        float number = Float.parseFloat(ed1.getText() + "");
        if (number < 0.0f){
            clean();
            clean = true;
            ed1.setText("Error");
            return;
        }
        ed1.setText(new DecimalFormat("#.###").format(java.lang.Math.sqrt(number)));
        repeat = true;
    }

    protected void negative(){
        if(ed1.getText().toString() != "" &&
            ed1.getText().toString().contains("-")){
            ed1.setText("" + ed1.getText().toString().substring(1));
        } else if(ed1.getText().toString() != ""){
            ed1.setText("-" + ed1.getText().toString());
        }
    }

    protected float division(float first, float second){
        float result = Float.MAX_VALUE;
        if(second != 0.0f){
            result = first/second;
        }
        return  result;
    }

    protected void clean(){
        first = Float.MAX_VALUE;
        second = Float.MAX_VALUE;
        result = 0.0f;
        repeat = false;
        ed1.setText("0");
    }

    protected void setButtons(){
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDisplay("9");
            }
        });

        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().contains("."))
                    return;
                sendToDisplay(".");
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation("+");
            }
        });

        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation("-");
            }
        });

        btn_Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation("*");
            }
        });

        btn_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation("/");
            }
        });

        btn_Sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqr();
            }
        });

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                negative();
            }
        });

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOperation();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
    }

}