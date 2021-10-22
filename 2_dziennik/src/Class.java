import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public class Class {
    private String name;
    private final List<Student> studentList;
    private final int studentsLimit;

    public Class(String name, int studentsLimit){
        this.name = name;
        this.studentsLimit = studentsLimit;
        this.studentList = new LinkedList<Student>();
    }

    public boolean addStudent(Student student){
        if(studentList.contains(student)){
            System.out.println("Student is already assigned to group "+name);
            return false;
        }

        else if(studentList.size() >= studentsLimit){
            System.err.println("Error: group has already reached it's limit!");
            return false;
        }

        studentList.add(student);
        System.out.println("Successfully added student to group " + name);
        return true;
    }

    public void addPoints(Student student, double points) {
        if (studentList.contains(student)) {
            student.addPoints(points);
            System.out.println("Points added successfully");
        }
        else{
            System.err.println("Error: student doesn't belong to group "+ name);
        }
    }

    public void changeCondition(Student student, StudentCondition condition){
        if (studentList.contains(student)) {
            student.setCondition(condition);
            System.out.println("Points added successfully");
        }
        else{
            System.err.println("Error: student doesn't belong to group "+ name);
        }
    }

    public void deletePoints(Student student, double points) {
        if (studentList.contains(student)) {
            student.deletePoints(points);
            System.out.println("Points deleted successfully");
        }
        else{
            System.err.println("Error: student doesn't belong to group "+ name);
        }
    }

//    public Student search(String surname){
//
//    }

//    public int countByCondition(StudentCondition condition){
//        return studentList.
//    }


    // print info for list
    public void print(){
        System.out.println("PRINTING "+name+" STUDENT'S INFO");
       for(Student student : studentList){
           student.print();
       }
    }

    private class ComparatorSurname implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }

    }

}
