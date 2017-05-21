package com.example.jorgeluis.anotador.Presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.example.jorgeluis.anotador.Activities.NoteActivityView;
import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.NoteActivity;
import com.example.jorgeluis.anotador.Services.NoteActivityServices;
import com.example.jorgeluis.anotador.Util.PaletOfColors;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jorge Luis on 19/05/2017.
 */
public class NotePresenter {

    private NoteActivityView view;
    private NoteActivityServices services;
    private boolean newNote;
    private boolean modifiedNote;
    private int position;
    private int qtyOfNotes = 0;


    public NotePresenter(NoteActivityView view, NoteActivityServices services) {
        this.view = view;
        this.services = services;
    }


    @Subscribe
    public void onResumeNoteActivityEvent(NoteActivity.onResumeNoteActivity event) {
        initializeUI();
    }


    @Subscribe
    public void onConfigurationChangedEvent(NoteActivity.onConfigurationChanged event) {
        initializeUI();
    }


    public void initializeUI() {

        Intent i = view.getActivity().getIntent();
        this.position = i.getIntExtra("Position", -1);
        services.setListOfNotes((List<Note>) i.getSerializableExtra("List"));
        qtyOfNotes = services.getListOfNotesSize();
        if (position == -1) {
            newNote = true;

        } else {
            newNote = false;
            view.setNoteTitle(services.getListOfNotes().get(position).getName());
            view.setNoteContentBackgroundColor(services.getListOfNotes().get(position).getBackgroundColor());
            view.setNoteTitleEnabled(false);
            view.setNoteContent(services.getListOfNotes().get(position).getContent());
         }

        modifiedNote = false;
    }

    @Subscribe
    public void onClickButtonSaveEvent(NoteActivityView.onClickButtonSave event) {
        int result;
        try {
            result = services.saveNote(event.title, event.content,event.backgroundColor, newNote, position);
            switch (result) {
                case 1:
                    view.finish();
                    break;
                case 2:
                    view.showToastMessage("Debe ingresar el título de la nota");
                    break;
                case 3:
                    view.showToastMessage("Ya existe una nota con el mismo nombre");
                    break;
            }
        } catch (IOException e) {
            view.showToastMessage("Error de lectura");
        } catch (Exception e) {
            view.showToastMessage("Error de lectura");
        }
    }

    @Subscribe
    public void onClickButtonDeleteEvent(NoteActivityView.onClickButtonDelete event) {

        if (!this.newNote) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getActivity());
            alertDialogBuilder.setMessage("¿Desea eliminar la nota?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                services.deleteNote(position);
                            } catch (IOException e) {
                                view.showToastMessage("Error al eliminar la nota");
                            }
                            view.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }

    @Subscribe
    public void onClickButtonShareEvent(NoteActivityView.onClickButtonShare event) {
        try {
            if ((event.title != null) && (event.content != null)) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = event.content;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, event.title);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                view.getActivity().startActivity(Intent.createChooser(sharingIntent, "Enviar por"));
            } else {
                view.showToastMessage("Se necesita un título y el texto de la nota para compartir");


            }
        } catch (Exception e) {
            view.showToastMessage("Error al compartir");

        }
    }

    @Subscribe
    public void onClickButtonPaintEvent(NoteActivityView.onClickButtonPaint event) {

        PaletOfColors paletOfColors = new PaletOfColors(view.getActivity());
        paletOfColors.closeOptionsMenu();
        paletOfColors.show();

    }

    @Subscribe
    public void onTextChangedEvent(NoteActivityView.onTextChanged event) {
        this.modifiedNote = true;
    }

    @Subscribe
    public void onBackPressedEvent(NoteActivity.onBackPressed event) {
        if (modifiedNote) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getActivity());
            alertDialogBuilder.setMessage("¿Desea salir sin guardar la nota?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            view.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            view.finish();
        }


    }
}

