public class Student implements Comparable<Student>{

     private String name;
     private String surname;
     private StudentCondition studentCondition;
     private int yearOfBirth;
     private double score;
     private int index; // numer albumu studenta

    public Student(String name, String surname, StudentCondition studentCondition, int yearOfBirth, double score, int index) {
        this.name = name;
        this.surname = surname;
        this.studentCondition = studentCondition;
        this.yearOfBirth = yearOfBirth;
        this.score = score;
        this.index = index;
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
        System.out.println("Student's condition: "+studentCondition);
    }

    public void addPoints(double points){
        score += points;
    }

    public void setCondition(StudentCondition condition) {
        this.studentCondition = condition;
    }

    public void deletePoints(double points){
        score -= points;
    }

    public String getSurname() {
        return surname;
    }
}
