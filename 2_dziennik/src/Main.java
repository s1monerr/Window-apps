import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
        public static void main(String[] args){
            StudentCondition condition_1 = StudentCondition.ABSENT;
            StudentCondition condition_2 = StudentCondition.PRESENT;

            Student student_1 = new Student("Szymon", "Rewilak", condition_1, 2000, 100, 401145);
            Student student_2 = new Student("Jan", "Kowalski", condition_2, 2000, 50, 123456);

            Class group_1 = new Class("GROUP 1", 10);
            group_1.addStudent(student_1);
            group_1.addStudent(student_2);

            group_1.print();
            try {
                group_1.search("Kowalski").print();
            }catch(NullPointerException e){
                //
            }
            try {
                group_1.search("NOBODY").print();
            }catch(NullPointerException e){
                //
            }

            System.out.println("BEFORE SORTING BY SURNAME: ");
            group_1.print();
            System.out.println("AFTER SORT");
            group_1.setStudentList(group_1.sortByName(group_1.getStudentList())); // setting sorted list as a list in object
            group_1.print();

            System.out.println("BEFORE SORTING BY SCORE: ");
            group_1.print();
            System.out.println("AFTER SORT");
            group_1.setStudentList(group_1.sortByScore(group_1.getStudentList())); // setting sorted list as a list in object
            group_1.print();

            // LAMBDA - BEST STUDENT STUDENT
            Student bestStudent = Collections.max(group_1.getStudentList(), ((o1, o2) -> {return Double.compare(o1.getScore(), o2.getScore());}));
            System.out.println("BEST STUDENT - with lambda: "+bestStudent.getName());
            System.out.println("BEST STUDENT - with method: "+group_1.max().getName());

            // container
            Class group_2 = new Class("GROUP 2", 20);
            ClassContainer container = new ClassContainer();

            container.addClass("g1", group_1);
            container.addClass("g2", group_2);

            // finding empty groups
            List<Class> emptyList = container.findEmpty();
            System.out.println("EMPTY GROUPS: ");
            for(Class c : emptyList) {
                System.out.println(c.getName());
            }

            container.summary();

            Class group_3 = new Class("GROUP 3", 2);
            Student student_3 = new Student("Katarzyna","Kowalska", condition_2, 2000, 400, 654321);

            // adding an added student to group
            group_1.addStudent(student_1);

            group_1.addStudent(student_3);
            List<Student> found = new LinkedList<>();
            found = group_1.searchPartial("KOWALSK");
            System.out.println("PRINTING AFTER PARTIAL SEARCH:");

            for(Student s : found){
                System.out.println(s.getName() + "  "+s.getSurname());
            }

            // student limit error

            group_3.addStudent(student_1);
            group_3.addStudent(student_2);
            group_3.addStudent(student_3);

        }
    }

