package io;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWrite <E>{
    public void write(ArrayList<E> eArrayList,String path){
        File file=new File(path);
        FileOutputStream fileOutputStream;
        ObjectOutputStream  objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(eArrayList);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public ArrayList<E> read(String path){
        File file=new File(path);
        FileInputStream fileInputStream;
        ObjectInputStream  objectInputStream;

        try {
            fileInputStream=new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);

            ArrayList<E> arrayList= (ArrayList<E>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return arrayList;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
