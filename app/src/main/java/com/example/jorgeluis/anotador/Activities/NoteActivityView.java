package com.example.jorgeluis.anotador.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.R;
import com.example.jorgeluis.anotador.Util.ActivityView;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jorge Luis on 19/05/2017.
 */
public class NoteActivityView extends ActivityView {


    @BindView(R.id.editTextNoteTitle) EditText editTextTitle;
    @BindView(R.id.editTextNoteContent) EditText editTextNoteContent;
    @BindView(R.id.buttonDelete) AppCompatImageButton buttonDelete;
    @BindView(R.id.buttonSave) AppCompatImageButton buttonSave;
    @BindView(R.id.buttonShare) AppCompatImageButton buttonShare;
    @BindView(R.id.buttonPaint) AppCompatImageButton buttonPaint;


    private Bus bus;
    private boolean notaModificada;
    private boolean nuevaNota;

    private int posicion;
    private int cantidadNotas;



    public NoteActivityView(Activity activity, Bus bus) {
        super(activity);
        this.bus = bus;
        ButterKnife.bind(this, activity);
        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postOnTextChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
            }

        });
    }

    public void postOnTextChanged(){
        bus.post(new onTextChanged());
    }
    @OnClick(R.id.buttonSave)
    public void onClickButtonSave(){
        bus.post(new onClickButtonSave(editTextTitle.getText().toString(),editTextNoteContent.getText().toString(),((ColorDrawable) editTextNoteContent.getBackground()).getColor())) ;

    }
    @OnClick(R.id.buttonDelete)
    public void onClickButtonDelete(){
        bus.post(new onClickButtonDelete());
    }

    @OnClick(R.id.buttonShare)
    public void onClickButtonShare(){
        bus.post(new onClickButtonShare(editTextTitle.getText().toString(),editTextNoteContent.getText().toString()));
    }

    @OnClick(R.id.buttonPaint)
    public void onClickButtonPaint(){
        bus.post(new onClickButtonPaint());
    }

    public void setNoteTitle(String title) {
        this.editTextTitle.setText(title);
    }

    public void setNoteContentBackgroundColor(int backgroundColor) {
        this.editTextNoteContent.setBackgroundColor(backgroundColor);
    }

    public void setNoteTitleEnabled(boolean b) {
        this.editTextTitle.setEnabled(b);
    }

    public void setNoteContent(String noteContent) {
        this.editTextNoteContent.setText(noteContent);
    }

    public void finish() {
        getActivity().finish();
    }


    public static class onClickButtonSave {
        public String title, content;
        public int backgroundColor;
        public onClickButtonSave(String title, String content,int backgroundColor)
        {
            this.title=title;
            this.content=content;
            this.backgroundColor=backgroundColor;

        }
    }
    public static class onClickButtonDelete {
    }
    public static class onClickButtonShare {
        public String title, content;
        public onClickButtonShare(String title, String content)
        {
            this.title=title;
            this.content=content;

        }
    }
    public static class onClickButtonPaint {
    }





    public void showToastMessage(String s) {
        Toast.makeText(getActivity(), s,Toast.LENGTH_SHORT).show();
    }

    public static class onTextChanged {
    }
}
