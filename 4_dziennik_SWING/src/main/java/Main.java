import Exceptions.NullFacadeException;

import java.awt.*;

public class Main {
//    public static ClassContainer classContainer;
//    public static Uczelnia uczelnia;
    public static void main(String[] args) throws NullFacadeException{
        ClassContainer classContainer = new ClassContainer();
        ClassGroup classGroup1 = new ClassGroup("GROUP 1", 20);
        Student Szymon = new Student("Szymon", "Rewilak",  2000, 100, 401145);
        Student noName = new Student("Noname", "Nosurname", 1999, 50, 123456);
        classContainer.addClass(classGroup1.getName(), classGroup1);
        classContainer.addStudent(Szymon, 1);
        classContainer.addStudent(noName, 1);
        Uczelnia facade = new Uczelnia(classContainer);
//        Uczelnia facade = null;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI(facade).setVisible(true);
                }catch (NullFacadeException e){
                    System.err.println(e);
                }
            }
        });
    }
}
