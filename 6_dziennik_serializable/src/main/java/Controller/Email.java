package Controller;

import java.io.Serializable;

public class Email implements Serializable {
    public String email;

    public Email(String email){
        this.email = email;
    }

    public void saveEmail(){
        FileOperations.checkFiles();
        FileOperations.saveObject(this, FileOperations.email.getAbsolutePath());
    }

}
