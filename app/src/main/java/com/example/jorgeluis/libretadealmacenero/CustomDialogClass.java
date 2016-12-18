package com.example.jorgeluis.libretadealmacenero;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    private Button button3,button4,button5,button6,button7,button8,button9,button10,button11,button12;
    private AppCompatButton button1, button2;
    private EditText contenido;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        button1 = (AppCompatButton) findViewById(R.id.btn_color1);
        button2 = (AppCompatButton) findViewById(R.id.btn_color2);
        button3 = (Button) findViewById(R.id.btn_color3);
        button4 = (Button) findViewById(R.id.btn_color4);

        button5 = (Button) findViewById(R.id.btn_color5);
        button6 = (Button) findViewById(R.id.btn_color6);
        button7 = (Button) findViewById(R.id.btn_color7);
        button8 = (Button) findViewById(R.id.btn_color8);

        button9 = (Button) findViewById(R.id.btn_color9);
        button10 = (Button) findViewById(R.id.btn_color10);
        button11 = (Button) findViewById(R.id.btn_color11);
        button12 = (Button) findViewById(R.id.btn_color12);

        contenido = (EditText) c.findViewById(R.id.editText);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
    }




   @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_color1:
                contenido.setBackgroundColor(button1.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color2:
                contenido.setBackgroundColor(button2.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color3:
                contenido.setBackgroundColor(button3.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color4:
                contenido.setBackgroundColor(button4.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color5:
                contenido.setBackgroundColor(button5.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color6:
                contenido.setBackgroundColor(button6.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color7:
                contenido.setBackgroundColor(button7.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color8:
                contenido.setBackgroundColor(button8.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color9:
                contenido.setBackgroundColor(button9.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color10:
                contenido.setBackgroundColor(button10.getCurrentTextColor());

                dismiss();
                break;
            case R.id.btn_color11:
                contenido.setBackgroundColor(button11.getCurrentTextColor());
                dismiss();
                break;
            case R.id.btn_color12:
                contenido.setBackgroundColor(button12.getCurrentTextColor());

                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}