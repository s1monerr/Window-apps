package Controller;// facade class

public class Uczelnia {
    private ClassContainer classContainer;


    public Uczelnia(ClassContainer classContainer){
        this.classContainer = classContainer;
    }

    public ClassContainer getClassContainer() {
        return classContainer;
    }
}
