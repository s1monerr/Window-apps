import Exceptions.NullFacadeException;
import Exceptions.OutOfRangeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GUI extends JFrame {
    //    Container pane;
    JPanel buttonsPanel;
    JTable studentsTable, groupTable, groupListTable;
    JScrollPane scrollPaneStudents, scrollPaneGroups, scrollPaneGroupList;

    JButton deleteButton, addStudentButton, deleteGroup, addGroupButton, sortStudents, modifyStudent, modifyGroup;
    private Uczelnia uczelnia;


    public GUI(Uczelnia facade) throws NullFacadeException {
        if (facade == null)
            throw new NullFacadeException("Fatal error - null facade pointer exception!");

        uczelnia = facade;

        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        scrollPaneStudents = new JScrollPane();
        scrollPaneGroups = new JScrollPane();
        studentsTable = new JTable();
        groupTable = new JTable();
        groupListTable = new JTable();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Szymon Rewilak - projekt 5");
//        setLayout(new FlowLayout());

        studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
        studentsTable.setCellSelectionEnabled(false);
        studentsTable.setRowSelectionAllowed(true);

        groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
        groupTable.setCellSelectionEnabled(false);
        groupTable.setRowSelectionAllowed(true);

//        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println("here "+groupTable.getSelectedRow());
//                LinkedList<Student> groupList = uczelnia.getClassContainer().getGroupList(groupTable.getSelectedRow());
//                groupListTable.setModel(new StudentTableModel(groupList));
//                scrollPaneGroupList = new JScrollPane();
//                scrollPaneGroupList.setViewportView(groupListTable);
//                getContentPane().add(groupListTable, BorderLayout.BEFORE_FIRST_LINE);
//                return;
//            }
//        });

        scrollPaneStudents.setViewportView(studentsTable);
        scrollPaneGroups.setViewportView(groupTable);
        getContentPane().add(scrollPaneStudents, BorderLayout.CENTER);
        getContentPane().add(scrollPaneGroups, BorderLayout.AFTER_LINE_ENDS);

        // buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        deleteButton = new JButton("Delete selected student");
        deleteGroup = new JButton("Delete selected group");
        addStudentButton = new JButton("Add student...");
        addGroupButton = new JButton("Add group...");
        sortStudents = new JButton("Sort students...");
        modifyStudent = new JButton("Modify selected student...");
        modifyGroup = new JButton("Modify selected group...");

        buttonsPanel.add(addGroupButton);
        buttonsPanel.add(addStudentButton);
        buttonsPanel.add(modifyStudent);
        buttonsPanel.add(modifyGroup);
        buttonsPanel.add(sortStudents);
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

        sortStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortStudentsActionPerformed(e);
            }
        });

        modifyStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyStudentActionPerformed(e);
            }
        });

        modifyGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyGroupActionPerformed(e);
            }
        });
    }

    // BUTTON FUNCTIONS
    private void deleteButtonActionPerformed(ActionEvent evt) {
        int row = studentsTable.getSelectedRow();
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                System.out.println(studentsTable.getSelectedRow());
                try {
                    Student toDelete = uczelnia.getClassContainer().allStudentList().get(studentsTable.getSelectedRow());
                    uczelnia.getClassContainer().deleteStudent(toDelete);
                    studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
                } catch (Exception e) {
                    //
                }

                return null;
            }
        };
        worker.execute();
    }

    private void addStudentButtonActionPerformed(ActionEvent evt) {
        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                InputLabelStudent input = new InputLabelStudent(uczelnia);
                input.setSize(300, 300);
                input.setTitle("Adding student:");
                input.setVisible(true);
                while (!input.getAddState())  // wait until student is added
                    Thread.sleep(500);

                studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
                return null;
            }
        };
        worker.execute();
    }

    private void deleteGroupActionPerformed(ActionEvent evt) {
        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    ClassGroup toDelete = uczelnia.getClassContainer().getClassByIndex(groupTable.getSelectedRow());
                    uczelnia.getClassContainer().removeClass(toDelete.getName());
                } catch (Exception e) {
                    //
                }
                groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
                studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
                return null;
            }
        };
        worker.execute();
    }

    private void addGroupButtonActionPerformed(ActionEvent e) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                InputLabelGroup input = new InputLabelGroup(uczelnia);
                input.setSize(300, 300);
                input.setTitle("Adding group");
                input.setVisible(true);

                while (!input.getAddState())
                    Thread.sleep(500);

                groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
                return null;
            }
        };
        worker.execute();
    }

    private void sortStudentsActionPerformed(ActionEvent e) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
//                studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().getSortedStudentList()));
                InputLabelSort input = new InputLabelSort(uczelnia);
                input.setSize(300, 200);
                input.setTitle("Sorting student list");
                input.setVisible(true);

                while (!input.getSortState())
                    Thread.sleep(500);

                studentsTable.setModel(new StudentTableModel(input.getSortedList()));
                return null;
            }
        };
        worker.execute();
    }

    private void modifyStudentActionPerformed(ActionEvent e) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    InputLabelModifyStudent input = new InputLabelModifyStudent(uczelnia);
                    input.setSize(300, 300);
                    input.setTitle("Modifying student:");
                    input.setVisible(true);
                    while (!input.getModifyState())  // wait until student is added
                        Thread.sleep(500);

                    studentsTable.setModel(new StudentTableModel(uczelnia.getClassContainer().allStudentList()));
                } catch (Exception exception) {
                    //
                }
                return null;
            }
        };
        worker.execute();
    }

    private void modifyGroupActionPerformed(ActionEvent e) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    InputLabelModifyGroup input = new InputLabelModifyGroup(uczelnia);
                    input.setSize(300, 300);
                    input.setTitle("Modifying group");
                    input.setVisible(true);

                    while (!input.getModifyState())
                        Thread.sleep(500);
                    groupTable.setModel(new ClassGroupTableModel(uczelnia.getClassContainer().allGroupsList()));
                }catch(Exception e){
                    //
                }
                return null;
            }
        };
        worker.execute();
    }


    private static class InputLabelStudent extends JFrame {
        JLabel labelName, labelSurname, labelIndex, labelYearOfBirth, labelScore, groupNumber, groupError, studentAdded, errorLabel;
        JTextField name, surname, index, yearOfBirth, score, group;
        JButton okButton;
        boolean addState = false; // student adding flag

        public InputLabelStudent(Uczelnia uczelnia) {
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

            labelScore = new JLabel("Student's score:");
            score = new JTextField(10);
            add(labelScore);
            add(score);

            groupNumber = new JLabel("Group number: ");
            group = new JTextField(10);
            add(groupNumber);
            add(group);

            okButton = new JButton("Add student.");
            add(okButton);

            groupError = new JLabel("Error - group out of bound!");
            studentAdded = new JLabel("Student added successfully!");
            errorLabel = new JLabel("Error - incorrect input data!");


            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String studentName = name.getText();
                        String studentSurname = surname.getText();
                        int studentIndex = Integer.parseInt(index.getText());
                        int studentBirth = Integer.parseInt(yearOfBirth.getText());
                        double studentScore = Double.parseDouble(score.getText());
                        int studentGroup = Integer.parseInt(group.getText());

                        uczelnia.getClassContainer().addStudent(new Student(studentName, studentSurname, studentBirth, studentScore, studentIndex), studentGroup);
                        getContentPane().removeAll();
                        add(studentAdded);
                        revalidate();
                        repaint();
                        addState = true;

                    } catch (NumberFormatException exception) {
                        getContentPane().removeAll();
                        add(errorLabel);

                        revalidate();
                        repaint();
                    } catch (OutOfRangeException exceptionRange) {
                        getContentPane().removeAll();
                        add(groupError);
                        revalidate();
                        repaint();
                    }
                }
            });
        }

        public boolean getAddState() {
            return addState;
        }
    }

    private class InputLabelGroup extends JFrame {
        JLabel groupName, groupLimit, groupAdded, errorLabel;
        JTextField name, limit;
        JButton okButton;
        boolean addState = false;

        public InputLabelGroup(Uczelnia uczelnia) {
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

            groupAdded = new JLabel("Group added successfully!");
            errorLabel = new JLabel("Error - incorrect input data!");

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String gName = name.getText();
                        int sLimit = Integer.parseInt(limit.getText());
                        uczelnia.getClassContainer().addClass(gName, new ClassGroup(gName, sLimit));
                        getContentPane().removeAll();
                        add(groupAdded);
                        revalidate();
                        repaint();
                        addState = true;
                    } catch (NumberFormatException exception) {
                        getContentPane().removeAll();
                        add(errorLabel);
                        revalidate();
                        repaint();
                    }
                }
            });
        }

        public boolean getAddState() {
            return addState;
        }
    }

    private class InputLabelSort extends JFrame {
        JLabel menu, sortedLabel;
        JButton sortSurname, sortScore;
        private boolean sortState = false;
        LinkedList<Student> sorted = new LinkedList<>();

        public InputLabelSort(Uczelnia uczelnia) {
            setLayout(new FlowLayout());
            sortedLabel = new JLabel("Sorted successfully!");
            menu = new JLabel("Choose sorting type:");
            add(menu);

            sortSurname = new JButton("Sort students by surname");
            add(sortSurname);
            sortSurname.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sorted = uczelnia.getClassContainer().getSortedStudentList(0);
                    getContentPane().removeAll();
                    add(sortedLabel);
                    revalidate();
                    repaint();
                    sortState = true;
                }
            });

            sortScore = new JButton("Sort students by score");
            add(sortScore);
            sortScore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sorted = uczelnia.getClassContainer().getSortedStudentList(1);
                    getContentPane().removeAll();
                    add(sortedLabel);
                    revalidate();
                    repaint();
                    sortState = true;
                }
            });
        }

        public boolean getSortState() {
            return sortState;
        }

        public LinkedList<Student> getSortedList() {
            return sorted;
        }
    }

    private class InputLabelModifyStudent extends JFrame {
        JLabel labelName, labelSurname, labelIndex, labelYearOfBirth, labelScore, groupNumber, groupError, studentModified, errorLabel;
        JTextField name, surname, index, yearOfBirth, score, group;
        JButton okButton;
        boolean modifyState = false; // student adding flag

        public InputLabelModifyStudent(Uczelnia uczelnia) {
            Student student = uczelnia.getClassContainer().allStudentList().get(studentsTable.getSelectedRow());
            setLayout(new FlowLayout());
            labelName = new JLabel("Student's name:");
            name = new JTextField(student.getName(), 10);
            add(labelName);
            add(name);

            labelSurname = new JLabel("Student's surname:");
            surname = new JTextField(student.getSurname(), 10);
            add(labelSurname);
            add(surname);

            labelIndex = new JLabel("Index number:");
            index = new JTextField(String.valueOf(student.getIndex()), 10);
            add(labelIndex);
            add(index);

            labelYearOfBirth = new JLabel("Year of birth:");
            yearOfBirth = new JTextField(String.valueOf(student.getYearOfBirth()), 10);
            add(labelYearOfBirth);
            add(yearOfBirth);

            labelScore = new JLabel("Student's score:");
            score = new JTextField(String.valueOf(student.getScore()), 10);
            add(labelScore);
            add(score);

            groupNumber = new JLabel("Group number: ");
            group = new JTextField(String.valueOf(student.getGroup()), 10);
            add(groupNumber);
            add(group);

            okButton = new JButton("Modify student.");
            add(okButton);

            groupError = new JLabel("Error - group out of bound!");
            studentModified = new JLabel("Student modified successfully!");
            errorLabel = new JLabel("Error - incorrect input data!");

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String studentName = name.getText();
                        String studentSurname = surname.getText();
                        int studentIndex = Integer.parseInt(index.getText());
                        int studentBirth = Integer.parseInt(yearOfBirth.getText());
                        double studentScore = Double.parseDouble(score.getText());
                        int studentGroup = Integer.parseInt(group.getText());

                        uczelnia.getClassContainer().changeStudentData(studentsTable.getSelectedRow(), studentName, studentSurname, studentBirth, studentScore, studentIndex, studentGroup);
                        getContentPane().removeAll();
                        add(studentModified);
                        revalidate();
                        repaint();
                        modifyState = true;

                    } catch (NumberFormatException exception) {
                        getContentPane().removeAll();
                        add(errorLabel);

                        revalidate();
                        repaint();
                    } catch (OutOfRangeException exceptionRange) {
                        getContentPane().removeAll();
                        add(groupError);
                        revalidate();
                        repaint();
                    }
                }
            });
        }

        public boolean getModifyState() {
            return modifyState;
        }
    }

    private class InputLabelModifyGroup extends JFrame{
        JLabel groupName, groupLimit, groupModified, errorLabel;
        JTextField name, limit;
        JButton okButton;
        boolean modifyState = false;

        public InputLabelModifyGroup(Uczelnia uczelnia) {
            setLayout(new FlowLayout());
            ClassGroup group = uczelnia.getClassContainer().getClassByIndex(groupTable.getSelectedRow());

            groupName = new JLabel("Group name: ");
            name = new JTextField(group.getName(), 10);
            add(groupName);
            add(name);

            groupLimit = new JLabel("Student limit: ");
            limit = new JTextField(String.valueOf(group.studentsLimit), 10);
            add(groupLimit);
            add(limit);

            groupModified = new JLabel("Group modified successfully!");
            errorLabel = new JLabel("Error - incorrect input data!");

            okButton = new JButton("Modify group");
            add(okButton);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String gName = name.getText();
                        int sLimit = Integer.parseInt(limit.getText());
                        uczelnia.getClassContainer().modifyClass(groupTable.getSelectedRow(), gName, sLimit);
                        getContentPane().removeAll();
                        add(groupModified);
                        revalidate();
                        repaint();
                        modifyState = true;
                    } catch (NumberFormatException exception) {
                        getContentPane().removeAll();
                        add(errorLabel);
                        revalidate();
                        repaint();
                    }
                }
            });

        }
        public boolean getModifyState(){return modifyState;}

    }
}
