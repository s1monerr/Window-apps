import java.awt.*;

public class Main {
//    public static ClassContainer classContainer;
//    public static Uczelnia uczelnia;
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
