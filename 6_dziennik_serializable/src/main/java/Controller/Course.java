package Controller;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {
    private String name;


    private LinkedList<Student> studentList;
    public int studentsLimit;
    private int enrolledStudents; // zmienna do przechowania ilosci studentow

    public Course(String name, int studentsLimit){
        this.name = name;
        this.studentsLimit = studentsLimit;
        this.studentList = new LinkedList<Student>();
    }

    public boolean addStudent(Student student){
        if(studentList.contains(student)){
            System.out.println("Controller.Student is already assigned to group "+name);
            return false;
        }

        else if(studentList.size() >= studentsLimit){
            System.err.println("Error: group " + name+" has already reached it's limit!");
            return false;
        }

        studentList.add(student);
        enrolledStudents++;
        System.out.println("Successfully added student to group " + name);
        return true;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean checkEmptyPlaces(){
        if(studentsLimit > enrolledStudents)
            return true;
        return false;
    }

//    public Student search(String surname){
//        Student temp = new Student(null,surname,0, 0, 0);
//
//        ComparatorSurname comp = new ComparatorSurname();
//
//        int i = 0;
//        for(Student s : studentList){
//            if(comp.compare(s, temp) == 0){
//                System.out.println("HERE");
//                return s;
//            }
//        }
//        System.out.println("Controller.Student not found.");
//        return null;
//    }
//
//    public List<Student> searchPartial(String string){
//        List<Student> toReturnList = new LinkedList<>();
//
//        for(Student s : studentList){
//            if(s.getName().toLowerCase().contains(string.toLowerCase()) || s.getSurname().toLowerCase().contains(string.toLowerCase(Locale.ROOT))){
//                toReturnList.add(s);
//            }
//        }
//        return toReturnList;
//    }


    // print info for list
    public void print(){
        System.out.println("PRINTING "+name+" STUDENT'S INFO");
       for(Student student : studentList){
           student.print();
       }
    }

    public List<Student> sortByName(List<Student> list){
        List<Student> toReturnList = new LinkedList<>();
        toReturnList = list;
        Collections.sort(list, new ComparatorSurname());
        return list;
    }

//    public List<Student> sortByScore(List<Student> list){
//        List<Student> toReturnList = new LinkedList<>();
//        toReturnList = list;
//        Collections.sort(list, new ComparatorScore());
//        Collections.reverse(list);
//        return list;
//    }

//    public Student max(){
//        return Collections.max(studentList, new ComparatorScore());
//    }

    public double capacity(){
        return 100*(studentList.size()/Double.valueOf(studentsLimit));
    }


    public LinkedList<Student> getStudentList() {
        return studentList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentList(LinkedList<Student> list){
        this.studentList = list;
    }

    public String getName() {
        return name;
    }

    public Object getStudentLimit() {
        return studentsLimit;
    }

    private class ComparatorSurname implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }

//    private class ComparatorScore implements Comparator<Student>{
//
//        @Override
//        public int compare(Student o1, Student o2) {
//            return Double.compare(o1.getScore(), o2.getScore());
//        }
//    }

//    public ComparatorScore getComparatorScore(){
//        return new ComparatorScore();
//    }

    public ComparatorSurname getComparatorSurname() {
        return new ComparatorSurname();
    }


}
