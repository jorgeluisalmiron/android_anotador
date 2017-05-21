package com.example.jorgeluis.anotador.Util;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge Luis on 18/05/2017.
 */
public  interface FileManager {

    boolean fileExists(String file) throws IOException;
    boolean exists(String[] files, String archsearch) throws IOException;
    void save(String fileName, String content)throws IOException ;
    <T extends Serializable> void saveSerializable(T objectToSave, String file)throws IOException;
    <T extends Serializable> T readSerializable(String fileName)throws IOException;
    <T extends Serializable> void readSerializableToList(String file, List list) throws IOException;
    String readTextFile(String file)throws IOException;
    boolean deleteFile(String file)throws IOException;
    void createFile(String file)throws IOException;


}
