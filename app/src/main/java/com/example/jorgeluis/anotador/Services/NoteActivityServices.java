package com.example.jorgeluis.anotador.Services;

import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.Util.FileManager;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jorge Luis on 19/05/2017.
 */
public class NoteActivityServices {

    private FileManager fileManager;
    private List<Note> listOfNotes;
    private final String FILE_NAME="notes.dat";

    public NoteActivityServices(FileManager fileManager) {

        this.fileManager=fileManager;
    }

    public void setListOfNotes(List<Note> listOfNotes){
        if (listOfNotes!=null)
           this.listOfNotes=listOfNotes;
        else
            this.listOfNotes = new ArrayList<>();
    }

    public List<Note> getListOfNotes()
    {
        return listOfNotes;
    }

    public int getListOfNotesSize(){

        if (listOfNotes==null)
            return 0;
        else
            return listOfNotes.size();
    }


    public int saveNote(String title, String content,int backgroundColor, boolean newNote, int position) throws IOException{

            if (title.equals(null) || title.equals("")) {
                return 2;
            }
            else
            {
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                if (newNote) {
                    listOfNotes.add(new Note(title, content, ft.format(dNow).toString(),backgroundColor));

                }
                else
                {
                    listOfNotes.get(position).setContent(content);
                    listOfNotes.get(position).setBackgroundColor(backgroundColor);

                }
                fileManager.saveSerializable((Serializable) listOfNotes, FILE_NAME);
                return 1;
            }

    }



    public boolean deleteNote(int position) throws IOException {

        listOfNotes.remove(position);
        fileManager.saveSerializable((Serializable) listOfNotes, FILE_NAME);
        return true;

    }
}
