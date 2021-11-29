package Controller;

import Exceptions.OutOfRangeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Comparable<Student>{

     private String name;
     private String surname;
     private Integer yearOfBirth;
     private int index; // numer albumu studenta
     private List<Grade> gradeList;
     private List<Course> courseList; // lista przedmiotow na ktore jest zapisany
    private List<CourseInfo> courseInfoList; // lista informacji o stanie przedmiotow
    private List<StateChanger> changerList;
    private String email;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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
        this.email = email;
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
        courseList.add(course);
        System.out.println("dodano info");
        courseInfoList.add(new CourseInfo(course, condition, 0f));
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
}
