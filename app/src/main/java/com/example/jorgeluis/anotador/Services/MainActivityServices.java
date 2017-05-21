package com.example.jorgeluis.anotador.Services;

import com.example.jorgeluis.anotador.Model.Note;
import com.example.jorgeluis.anotador.Util.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jorge Luis on 18/05/2017.
 */
public class MainActivityServices {


    private static String FILE_NAME="notes.dat";
    private List<Note> listOfNotes;
    private FileManager fileManager;
    public MainActivityServices(FileManager fileManager)  {
        this.fileManager=fileManager;
        try {
             fileManager.createFile(FILE_NAME); //Create the file if it doesn´t exists

         } catch (IOException e) {
            e.printStackTrace();
         }

    }

    public List<Note> getListOfNotes()
    {
        return listOfNotes;
    }

    public List<Note> retrieveListOfNotes() throws IOException
    {
          listOfNotes = (List<Note>) fileManager.readSerializable(FILE_NAME);
          return listOfNotes;
    }


}


