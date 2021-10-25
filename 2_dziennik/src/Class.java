import java.util.*;

public class Class {
    private String name;


    private List<Student> studentList;
    public final int studentsLimit;

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
            System.err.println("Error: group " + name+" has already reached it's limit!");
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

    public void getStudent(Student student){
        if(student.getScore() == 0) {
            System.out.println("Student with no points - deleted successfully");
            studentList.remove(student);
            student = null;
        }

        else{
            studentList.remove(student);
        }

    }

    public void changeCondition(Student student, StudentCondition condition){
        if (studentList.contains(student)) {
            student.setCondition(condition);
            System.out.println("Condition changed successfully");
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

    public Student search(String surname){
        Student temp = new Student(null, surname, null, 0, 0, 0);

        ComparatorSurname comp = new ComparatorSurname();

        int i = 0;
        for(Student s : studentList){
            if(comp.compare(s, temp) == 0){
                System.out.println("HERE");
                return s;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    public List<Student> searchPartial(String string){
        List<Student> toReturnList = new LinkedList<>();

        for(Student s : studentList){
            if(s.getName().toLowerCase().contains(string.toLowerCase()) || s.getSurname().toLowerCase().contains(string.toLowerCase(Locale.ROOT))){
                toReturnList.add(s);
            }
        }
        return toReturnList;
    }

    public int countByCondition(StudentCondition condition){
        int countToReturn = 0;

        for(Student s : studentList){
            if(s.getCondition() == condition){
                countToReturn++;
            }
        }
        return countToReturn;
    }


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

    public List<Student> sortByScore(List<Student> list){
        List<Student> toReturnList = new LinkedList<>();
        toReturnList = list;
        Collections.sort(list, new ComparatorScore());
        Collections.reverse(list);
        return list;
    }

    public Student max(){
        return Collections.max(studentList, new ComparatorScore());
    }

    public double capacity(){
        return 100*(studentList.size()/Double.valueOf(studentsLimit));
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> list){
        this.studentList = list;
    }

    public String getName() {
        return name;
    }

    private class ComparatorSurname implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }

    private class ComparatorScore implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return Double.compare(o1.getScore(), o2.getScore());
        }
    }

    public ComparatorScore getComparatorScore(){
        return new ComparatorScore();
    }


}
