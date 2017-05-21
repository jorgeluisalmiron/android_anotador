package com.example.jorgeluis.anotador;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jorgeluis.anotador.Activities.NoteActivityView;
import com.example.jorgeluis.anotador.Presenter.MainPresenter;
import com.example.jorgeluis.anotador.Presenter.NotePresenter;
import com.example.jorgeluis.anotador.Services.MainActivityServices;
import com.example.jorgeluis.anotador.Services.NoteActivityServices;
import com.example.jorgeluis.anotador.Util.AndroidFileManager;
import com.example.jorgeluis.anotador.Util.BusProvider;
import com.squareup.otto.Bus;

import java.io.IOException;


public class NoteActivity extends AppCompatActivity  {


    private NotePresenter presenter;
    private NoteActivityView view;
    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        view = new NoteActivityView(this, BusProvider.getInstance());
        bus = BusProvider.getInstance();
        presenter = new NotePresenter(view,new NoteActivityServices(new AndroidFileManager(this)));


    }
    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
        bus.post(new onResumeNoteActivity());
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }




    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_note);
        bus.post(new onConfigurationChanged());
    }



    @Override
    public void onBackPressed() {
        bus.post(new onBackPressed());


    }

    public static class onResumeNoteActivity {
    }

    public static class onConfigurationChanged {
    }

    public static class onBackPressed {
    }
}

