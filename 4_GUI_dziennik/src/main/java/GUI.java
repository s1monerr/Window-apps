import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    //    Container pane;
    JPanel buttonsPanel;
    JTable studentsTable;
    JScrollPane scrollPaneStudents, scrollPaneGroups;
    JTable groupTable;

    JButton deleteButton, addStudentButton, deleteGroup, addGroupButton;
    private Uczelnia uczelnia;


    public GUI(){
//        setLayout(new GridLayout());
//        frame = new JFrame("Szymon Rewilak, projekt 5");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ClassContainer classContainer = new ClassContainer();
        ClassGroup classGroup1 = new ClassGroup("GROUP 1", 20);
        Student Szymon = new Student("Szymon", "Rewilak",  2000, 100, 401145);
        Student noName = new Student("Noname", "Nosurname", 1999, 50, 123456);
        classGroup1.addStudent(Szymon);
        classGroup1.addStudent(noName);
        classContainer.addClass(classGroup1.getName(), classGroup1);
        uczelnia = new Uczelnia(classContainer);

        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents(){
        scrollPaneStudents = new JScrollPane();
        scrollPaneGroups = new JScrollPane();
        studentsTable = new JTable();
        groupTable = new JTable();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Szymon Rewilak - projekt 5");
//        setLayout(new FlowLayout());

        studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
        studentsTable.setCellSelectionEnabled(false);
        studentsTable.setRowSelectionAllowed(true);

        groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
        groupTable.setCellSelectionEnabled(false);
        groupTable.setRowSelectionAllowed(true);

        scrollPaneStudents.setViewportView(studentsTable);
        scrollPaneGroups.setViewportView(groupTable);
        getContentPane().add(scrollPaneStudents, BorderLayout.CENTER);
        getContentPane().add(scrollPaneGroups, BorderLayout.AFTER_LINE_ENDS);

        // buttons
        buttonsPanel = new JPanel();
        deleteButton = new JButton("Delete selected student");
        deleteGroup = new JButton("Delete selected group");
        addStudentButton = new JButton("Add student");
        addGroupButton = new JButton("Add group");

        buttonsPanel.add(addGroupButton);
        buttonsPanel.add(addStudentButton);
        buttonsPanel.add(deleteGroup);
        buttonsPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);
        pack();

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        deleteGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGroupActionPerformed(e);
            }
        });

        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGroupButtonActionPerformed(e);
            }
        });
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        int row = studentsTable.getSelectedRow();
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                System.out.println(studentsTable.getSelectedRow());
                Student toDelete = uczelnia.getClassContainer().allStudentList().get(studentsTable.getSelectedRow());
                uczelnia.getClassContainer().deleteStudent(toDelete);
                studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));

                return null;
            }
        };
        worker.execute();
    }

    private void addStudentButtonActionPerformed(ActionEvent evt){
        SwingWorker worker = new SwingWorker(){

            @Override
            protected Object doInBackground() throws Exception {
                InputLabelStudent input = new InputLabelStudent(uczelnia);
                input.setSize(300, 300);
                input.setTitle("Adding student:");
                input.setVisible(true);
                while(!input.getAddState())  // wait until student is added
                    Thread.sleep(500);

                studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
                return null;
            }
        };
        worker.execute();
    }

    private void deleteGroupActionPerformed(ActionEvent evt){
        SwingWorker worker = new SwingWorker(){

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    ClassGroup toDelete = uczelnia.getClassContainer().getClassByIndex(groupTable.getSelectedRow());
                    uczelnia.getClassContainer().removeClass(toDelete.getName());
                }catch(Exception e){
                    System.out.println("Error: "+e);
                }
                groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
                studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
                return null;
            }
        };
        worker.execute();
    }

    private void addGroupButtonActionPerformed(ActionEvent e){
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                InputLabelGroup input = new InputLabelGroup(uczelnia);
                input.setSize(300, 300);
                input.setTitle("Adding group");
                input.setVisible(true);

                while(!input.getAddState())
                    Thread.sleep(500);

                groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
                return null;
            }
        };
        worker.execute();
    }


    private static class InputLabelStudent extends JFrame{
        JLabel labelName, labelSurname, labelIndex, labelYearOfBirth, groupNumber;
        JTextField name, surname, index, yearOfBirth, group;
        JButton okButton;
        boolean addState = false; // student adding flag

        public InputLabelStudent(Uczelnia uczelnia){
            setLayout(new FlowLayout());
            labelName = new JLabel("Student's name:");
            name = new JTextField(10);
            add(labelName);
            add(name);

            labelSurname = new JLabel("Student's surname:");
            surname = new JTextField(10);
            add(labelSurname);
            add(surname);

            labelIndex = new JLabel("Index number:");
            index = new JTextField(10);
            add(labelIndex);
            add(index);

            labelYearOfBirth = new JLabel("Year of birth:");
            yearOfBirth = new JTextField(10);
            add(labelYearOfBirth);
            add(yearOfBirth);

            groupNumber = new JLabel("Group number: ");
            group = new JTextField(10);
            add(groupNumber);
            add(group);

            okButton = new JButton("Add student.");
            add(okButton);


            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String studentName = name.getText();
                    String studentSurname = surname.getText();
                    int studentIndex = Integer.parseInt(index.getText());
                    int studentBirth = Integer.parseInt(yearOfBirth.getText());
                    int studentGroup = Integer.parseInt(group.getText());
                    uczelnia.getClassContainer().addStudent(new Student(studentName, studentSurname, studentBirth, 0, studentIndex), studentGroup);
                    addState = true;
                }
            });
        }

        public boolean getAddState(){
            return addState;
        }
    }

    private class InputLabelGroup extends JFrame{
        JLabel groupName, groupLimit;
        JTextField name, limit;
        JButton okButton;
        boolean addState = false;

        public InputLabelGroup(Uczelnia uczelnia){
            setLayout(new FlowLayout());
            groupName = new JLabel("Group name: ");
            name = new JTextField(10);
            add(groupName);
            add(name);

            groupLimit = new JLabel("Student limit: ");
            limit = new JTextField(10);
            add(groupLimit);
            add(limit);

            okButton = new JButton("Add group");
            add(okButton);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String gName = name.getText();
                    int sLimit = Integer.parseInt(limit.getText());
                    uczelnia.getClassContainer().addClass(gName, new ClassGroup(gName, sLimit));
                    addState = true;
                }
            });
        }
        public boolean getAddState(){
            return addState;
        }
    }

}
