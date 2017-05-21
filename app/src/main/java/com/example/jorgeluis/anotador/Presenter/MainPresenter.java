package com.example.jorgeluis.anotador.Presenter;

import android.content.Intent;
import android.util.Log;


import com.example.jorgeluis.anotador.AboutActivity;
import com.example.jorgeluis.anotador.Activities.MainActivityView;
import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.NoteActivity;
import com.example.jorgeluis.anotador.MainActivity;
import com.example.jorgeluis.anotador.R;
import com.example.jorgeluis.anotador.Services.MainActivityServices;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Jorge Luis on 18/05/2017.
 */
public class MainPresenter {


    private MainActivityView view;
    private MainActivityServices services;


    public MainPresenter(MainActivityView view, MainActivityServices services) {

        this.view = view;
        this.services=services;

    }

    @Subscribe
    public void onResumeMainActivityEvent(MainActivity.onResumeMainActivity event){

        List<Note> listOfNotes = services.retrieveListOfNotes();
        if (listOfNotes==null){
            view.setTextViewEmptyListVisible(true);
            view.setRecyclerViewVisible(false);

        }
        else {

            if (listOfNotes.size() == 0) {

                view.setTextViewEmptyListVisible(true);
                view.setRecyclerViewVisible(false);

            }
            else {
                view.setNoteAdapter(listOfNotes);
                view.setTextViewEmptyListVisible(false);
                view.setRecyclerViewVisible(true);
            }
        }
       // view.fabIconAnimate();
    }

    @Subscribe
    public void onItemClickEvent (MainActivityView.clickListItem event)    {
        Intent i = new Intent(view.getContext(), NoteActivity.class);
        i.putExtra("Position",event.position);
        i.putExtra("List", (Serializable) services.getListOfNotes());
        view.getActivity().startActivity(i);
    }

    @Subscribe
    public void onClickNewNote (MainActivityView.clickNewNote event){
        Intent i = new Intent(view.getContext(), NoteActivity.class);
        i.putExtra("Position",-1);
        i.putExtra("List", (Serializable) services.getListOfNotes());
        view.getActivity().startActivity(i);
    }


    @Subscribe

    public void onOptionsItemSelectedEvent (MainActivity.onOptionsItemSelectedClick event) {
        int id = event.itemId;

        if (id == R.id.action_about) {

            Intent i = new Intent(view.getContext(), AboutActivity.class);
            view.getActivity().startActivity(i);

        }
    }



}
