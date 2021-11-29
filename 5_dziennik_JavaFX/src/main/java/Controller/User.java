package Controller;

import java.util.List;

public class User {
    private Uczelnia uczelnia; // Fasada

    public User(Uczelnia uczelnia){
        this.uczelnia = uczelnia;
    }

    public List<Course> viewCourses(){
        return uczelnia.getClassContainer().allCoursesList();
    }

    public Uczelnia getUczelnia() {
        return this.uczelnia;
    }
}
