package Controller;

import java.io.*;

public class FileOperations {

    public static File stateChanger = new File("stateChanger");
    public static File student = new File("student");
    public static File email = new File("email");
    public static File courseInfo = new File("courseInfo");

    // check if files exist
    // return false if program is run for the first time
    public static boolean checkFiles(){
        boolean firstUseFlag = false;

        if(!student.exists()){
            firstUseFlag = true;
            try{
                student.createNewFile();
            } catch (IOException e) {
                System.out.println("Student file error!");
                e.printStackTrace();
                return false;
            }
        }

        if(!student.exists()){
            firstUseFlag = true;
            try{
                student.createNewFile();
            } catch (IOException e) {
                System.out.println("Student file error!");
                e.printStackTrace();
                return false;
            }
        }

        if(!email.exists()){
            firstUseFlag = true;
            try{
                email.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        if(!courseInfo.exists()){
            try{
                courseInfo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return firstUseFlag; // returns true if file is used for the first time
    }

    public static <T> void saveObject(T object, String filename) {
        File file = new File(filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T readObject(Class<T> cls, String filename) {
        File file = new File(filename);
        if (file.exists()) {
            if (file.length() != 0) {
                try (FileInputStream fileInputStream = new FileInputStream(file);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    T t = cls.cast(objectInputStream.readObject());
                    return t;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
