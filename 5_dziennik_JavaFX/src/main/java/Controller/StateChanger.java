package Controller;

import javafx.scene.control.ComboBox;

public class StateChanger {
    private Course course;
    private StudentCondition courseCondition;
    private ComboBox<StudentCondition> condition;

    public StudentCondition getCourseCondition() {
        return courseCondition;
    }

    public StateChanger(Course course, StudentCondition courseCondition){
        this.course = course;
        this.courseCondition = courseCondition;
    }

    public void setCondition(ComboBox<StudentCondition> condition) {
        this.condition = condition;
    }

    public String getCourse() {
        return course.getName();
    }

    public Course getCourseRoot(){
        return course;
    }

    public ComboBox<StudentCondition> getCondition() {
        return condition;
    }

    public void setCourseCondition(StudentCondition courseCondition) {
        this.courseCondition = courseCondition;
    }
}
