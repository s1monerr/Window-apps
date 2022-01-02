package Controller;

import Exceptions.OutOfRangeException;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student>, Serializable {

     private String name;
     private String surname;
     private Integer yearOfBirth;
     private int index; // numer albumu studenta
     private List<Grade> gradeList;
     private List<Course> courseList; // lista przedmiotow na ktore jest zapisany
    private List<CourseInfo> courseInfoList; // lista informacji o stanie przedmiotow
    private List<StateChanger> changerList;
    private Email email;

    private Integer group;

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<StateChanger> getChangerList() {
        return changerList;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getEmail() {
        return email.email;
    }

    public Student(String name, String surname, int yearOfBirth, int index, String email) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.index = index;
        this.group = null;
        gradeList = new ArrayList<>();
        courseList = new ArrayList<>();
        courseInfoList = new ArrayList<>();
        this.email = new Email(email);
    }

    public void setChanger(List<StateChanger> changer) {
        this.changerList = changer;
    }

    @Override
    public String toString(){
        return surname + " ";
    }

    @Override
    public int compareTo(Student o) {
        return surname.compareTo(o.surname);
    }

    public void print(){
        System.out.println("==========");
        System.out.print("STUDENT'S INFO: ");
        System.out.println("Name: "+name+", surname: "+surname+", born in "+yearOfBirth);
    }


    public List<CourseInfo> getCourseInfoList() {
        return courseInfoList;
    }

    public void addGrade(float grade, String name, String date, Course course){
        if(grade > 5 || grade < 2)
            throw new OutOfRangeException("Error: grade out of grade range!");
        gradeList.add(new Grade(grade, name, date, course));
        countCourseAverage(course);
    }

    public void addCourse(Course course, StudentCondition condition){
        if(!courseList.contains(course)) {
            courseList.add(course);
            System.out.println("dodano info");
            courseInfoList.add(new CourseInfo(course, condition, 0f));
        }
        else {
            System.out.println("errororoororor");
        }
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public Integer getIndex() {
        return index;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public List<Grade> getGradeListByName(String name) {
        List<Grade> toReturn = new ArrayList<>();
        for(Grade g : gradeList){
            if(g.getCourse().getName() == name){
                toReturn.add(g);
            }
        }
        return toReturn;
    }

    private void countCourseAverage(Course course){
        float gradeSum = 0f;
        float gradeCount = 0;
        for(Grade g : gradeList){
            if(g.getCourse() == course){
                gradeCount++;
                gradeSum += g.getGrade();
            }
        }
        float average = gradeSum/gradeCount;

        for(int i = 0; i < courseInfoList.size(); i++){
            if(courseInfoList.get(i).getCourseReal() == course){
                courseInfoList.get(i).setAverage(average);
            }
        }
    }

    public void setChangerStudentCondition(Course course, StudentCondition condition){
        for(StateChanger c : changerList){
            if(c.getCourseRoot() == course){
                c.setCourseCondition(condition);
                break;
            }
        }
    }

    public void setCourseCondition(Course course, StudentCondition condition){
        for(CourseInfo c : courseInfoList){
            if(c.getCourse() == course.getName()){
                c.setCondition(condition);
                break;
            }
        }
    }

    public StateChanger getChangerByCourse(Course course){
        for(StateChanger c : changerList){
            if(c.getCourseRoot() == course){
                return c;
            }
        }
        return null;
    }

    public void saveStateChangers(){
        FileOperations.checkFiles();
        FileOperations.saveObject(this.changerList, FileOperations.stateChanger.getAbsolutePath());
    }

    public void saveEmail() {
        FileOperations.checkFiles();
        FileOperations.saveObject(this.email, FileOperations.email.getAbsolutePath());
    }

    public void setCourseInfoList(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }

    public void saveCourseList(){
        FileOperations.checkFiles();
        FileOperations.saveObject(courseInfoList, FileOperations.courseInfo.getAbsolutePath());
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void updateChangerList(){
        for(CourseInfo c : courseInfoList){
            ComboBox<StudentCondition> tempComboBox = new ComboBox<StudentCondition>();
            tempComboBox.getItems().add(StudentCondition.ZAPISANY);
            tempComboBox.getItems().add(StudentCondition.PRZEPISANY);
            tempComboBox.getItems().add(StudentCondition.WYPISANY);

            System.out.println(c.getCourse());
            tempComboBox.getSelectionModel().select(c.getCondition());
            StateChanger stateChanger = new StateChanger(c.getCourseReal(), c.getCondition());
            stateChanger.setCondition(tempComboBox);
        }
    }
}
