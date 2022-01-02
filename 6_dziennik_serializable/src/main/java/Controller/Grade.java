package Controller;

import java.io.Serializable;
import java.util.Date;

public class Grade implements Serializable {
    float grade;
    String name;
    String date;
    Course course;

    public Grade(float grade, String name, String date, Course course){
        this.grade = grade;
        this.name = name;
        this.date = date;
        this.course = course;
    }

    public Course getCourse(){return course;}

    public float getGrade() {
        return grade;
    }

    public String getDate(){ return date; }

    public String getName() {
        return name;
    }
}
