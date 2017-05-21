package com.example.jorgeluis.anotador.Services;

import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.Util.FileManager;
import com.squareup.otto.Bus;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jorge Luis on 18/05/2017.
 */
public class MainActivityServices {


    private static String INDEX_FILE="index.idx";
    private List<Note> listOfNotes;
    private FileManager fileManager;
    public MainActivityServices(FileManager fileManager)  {
        this.fileManager=fileManager;
        try {
             fileManager.createFile(INDEX_FILE);

         } catch (IOException e) {
            e.printStackTrace();
         }

       /* try {
            listOfNotes = (List<Note>) fileManager.readSerializable(INDEX_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void validateIndex() throws IOException {


        if (!fileManager.fileExists(INDEX_FILE))
        {

            fileManager.createFile(INDEX_FILE);

        }

    }



    public List<Note> getListOfNotes()
    {
        return listOfNotes;
    }

    public List<Note> retrieveListOfNotes()
    {
        try {
            listOfNotes = (List<Note>) fileManager.readSerializable(INDEX_FILE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfNotes;
    }
}


