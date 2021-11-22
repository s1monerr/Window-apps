public class Student implements Comparable<Student>{

     private String name;
     private String surname;
     private StudentCondition studentCondition;
     private Integer yearOfBirth;
     private double score;
     private int index; // numer albumu studenta


    private Integer group;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Student(String name, String surname, int yearOfBirth, double score, int index) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.score = score;
        this.index = index;
        this.group = null;
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
        System.out.println("Student's condition: "+studentCondition+" , SCORE: "+score);
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

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public StudentCondition getCondition(){
        return studentCondition;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getGroup(){return group;}

    public void setGroup(Integer group) {
        this.group = group;
    }
}
