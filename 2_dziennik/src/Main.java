 public class Main {
        public static void main(String[] args){
            StudentCondition condition_1 = StudentCondition.ABSENT;

            Student student_1 = new Student("Szymon", "Rewilak", condition_1, 2000, 100, 401145);

            Class group_1 = new Class("GROUP 1", 10);
            group_1.addStudent(student_1);
            group_1.print();
        }
    }

