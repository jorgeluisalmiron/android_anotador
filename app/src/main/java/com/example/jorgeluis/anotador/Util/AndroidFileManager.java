package com.example.jorgeluis.anotador.Util;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jorge Luis on 19/05/2017.
 */
public class AndroidFileManager  implements FileManager{
    Context context;

    public AndroidFileManager(Context context) {
        this.context = context;
    }



    @Override
    public  boolean fileExists(String file) {
        File f = new File(file);
        if (f.exists() && f.isDirectory()) {
            return true;
        } else
            return false;
    }


    @Override
    public boolean exists(String[] archivos, String archsearch) {
        for (int f = 0; f < archivos.length; f++)
            if (archsearch.equals(archivos[f]))
                return true;
        return false;
    }

    @Override
    public  void save(String file, String content) throws IOException {
        OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(
                file, Activity.MODE_PRIVATE));
        archivo.write(content);
        archivo.flush();
        archivo.close();
    }

    @Override
    public  <T extends Serializable> void saveSerializable(T objectToSave, String fileName) throws IOException{
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objectToSave);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends Serializable> T readSerializable(String fileName) throws IOException{
        T objectToReturn = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectToReturn = (T) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectToReturn;
    }

    @Override
    public <T extends Serializable> void readSerializableToList(String fileName, List list) throws IOException {
        T objectToReturn = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectToReturn = (T) objectInputStream.readObject();


            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        list= (List) objectToReturn;
    }

    @Override
    public  String readTextFile(String file) throws IOException {

        InputStreamReader isr = new InputStreamReader(context.openFileInput(file));
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();

        String allLines = "";
        while (line != null) {
            allLines = allLines + line + "\n";
            line = br.readLine();
        }
        br.close();
        isr.close();

        return allLines.toString();

    }


    @Override
    public  boolean deleteFile(String fileName) throws IOException{
        File file = new File(fileName);

        if(file.delete()){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public  void createFile(String file) throws IOException{
        List listOfFiles = Arrays.asList(context.fileList());
        if (!listOfFiles.contains(file)) {

                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(file, Activity.MODE_PRIVATE));

                osw.flush();
                osw.close();

        }
    }
}
