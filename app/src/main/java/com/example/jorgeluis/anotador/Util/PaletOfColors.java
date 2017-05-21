package com.example.jorgeluis.anotador.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.jorgeluis.anotador.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaletOfColors extends Dialog  {

    public Activity activity;
    public Dialog d;

    @BindView(R.id.btn_color1) Button button1;
    @BindView(R.id.btn_color2) Button button2;
    @BindView(R.id.btn_color3) Button button3;
    @BindView(R.id.btn_color4) Button button4;
    @BindView(R.id.btn_color5) Button button5;
    @BindView(R.id.btn_color6) Button button6;
    @BindView(R.id.btn_color7) Button button7;
    @BindView(R.id.btn_color8) Button button8;
    @BindView(R.id.btn_color9) Button button9;
    @BindView(R.id.btn_color10) Button button10;
    @BindView(R.id.btn_color11) Button button11;
    @BindView(R.id.btn_color12) Button button12;
    private EditText editTextNoteContent;


    public PaletOfColors(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
        editTextNoteContent = (EditText) activity.findViewById(R.id.editTextNoteContent);

      /*  button1.setOnClickListener(this);
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
        button12.setOnClickListener(this);*/
    }



    @OnClick(R.id.btn_color1)
    public void  btn_color1 () {
        editTextNoteContent.setBackgroundColor(button1.getCurrentTextColor());
        dismiss();
    }
    @OnClick(R.id.btn_color2)
    public void  btn_color2 () {
        editTextNoteContent.setBackgroundColor(button2.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color3)
    public void  btn_color3 () {
        editTextNoteContent.setBackgroundColor(button3.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color4)
    public void  btn_color4 () {
        editTextNoteContent.setBackgroundColor(button4.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color5)
    public void  btn_color5 () {
        editTextNoteContent.setBackgroundColor(button5.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color6)
    public void  btn_color6 () {
        editTextNoteContent.setBackgroundColor(button6.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color7)
    public void  btn_color7 () {
        editTextNoteContent.setBackgroundColor(button7.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color8)
    public void  btn_color8 () {
        editTextNoteContent.setBackgroundColor(button8.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color9)
    public void  btn_color9 () {
        editTextNoteContent.setBackgroundColor(button9.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color10)
    public void  btn_color10 () {
        editTextNoteContent.setBackgroundColor(button10.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color11)
    public void  btn_color11 () {
        editTextNoteContent.setBackgroundColor(button11.getCurrentTextColor());
        dismiss();

    }
    @OnClick(R.id.btn_color12)
    public void  btn_color12 () {
        editTextNoteContent.setBackgroundColor(button12.getCurrentTextColor());
        dismiss();

    }


    /*
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
    */
}