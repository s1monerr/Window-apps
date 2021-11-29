package Application;

import Controller.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    private Student user;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private ComboBox courseComboBox;

    @FXML
    private Button mainButtonHandle;

    @FXML
    private Button changeInfoButton;

    @FXML
    private Button myInfoButton;


    @FXML
    public void handleCourseComboBoxAction(){
        try {
            if(courseComboBox.getValue().toString() != "Choose course") {

                if (courseComboBox.getValue().toString() == "Course list") {
                    List<CourseInfo> infoList = user.getCourseInfoList();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("course-list-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        CourseInfoListController controller = fxmlLoader.getController();
                        controller.setCourseInfoTable(infoList);

                        stage.setTitle("Course list");
                        stage.setScene(scene);
                        stage.show();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if(courseComboBox.getValue().toString() == "RISKY COURSES!"){
                    List<CourseInfo> riskyCourseList = new ArrayList<>();
                    for(CourseInfo c : user.getCourseInfoList()){
                        if(c.getAverage() < 3f && c.getCondition() != StudentCondition.OCZEKUJACY && c.getCondition() != StudentCondition.WYPISANY)
                            riskyCourseList.add(c);
                    }
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("course-list-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        CourseInfoListController controller = fxmlLoader.getController();
                        controller.setCourseInfoTable(riskyCourseList);

                        stage.setTitle("Risky course list");
                        stage.setScene(scene);
                        stage.show();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    List<Grade> gradeList = user.getGradeListByName(courseComboBox.getValue().toString());
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("chosen-course-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        CourseTableController controller = fxmlLoader.getController();
                        controller.setGradeList(gradeList);

                        stage.setTitle(courseComboBox.getValue().toString() + " grades list");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(Exception e){
            //
        }

    }

    public void myInfoButtonHandle() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("my-info-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());

            MyInfoViewController controller = fxmlLoader.getController();
            controller.setUser(user);

            stage.setTitle("My info");
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            //
        }
    }

    public void buttonInfoHandle(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("change-info-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);

            ChangeInfoController controller = fxmlLoader.getController();
            controller.setStateChangerList(user.getChangerList());
            controller.setUser(user);
            stage.setTitle("Course settings");
            stage.setScene(scene);
            stage.showAndWait();

            // jesli nastapila jakas zmiana
            if(controller.getChangeFlag()){
                courseComboBox.getItems().clear();
                for (Course course : user.getCourseList()) {
                    courseComboBox.getItems().add(course.getName());
                }
                courseComboBox.getItems().add("Course list");
                courseComboBox.setPromptText("Choose course");
                courseComboBox.getItems().add("RISKY COURSES!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mailButtonHandle(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mail-change-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());

            MailChangeViewController controller = fxmlLoader.getController();
            controller.setUser(user);

            stage.setTitle("My info");
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            //
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user = initValues();
        courseComboBox.getItems().clear();
        for (Course course : user.getCourseList()) {
            courseComboBox.getItems().add(course.getName());
        }
        courseComboBox.getItems().add("Course list");
        courseComboBox.getItems().add("RISKY COURSES!");

        for(StateChanger c : user.getChangerList()){
            ComboBox<StudentCondition> tempComboBox = new ComboBox<StudentCondition>();
            StudentCondition condition = c.getCourseCondition();
                tempComboBox.getItems().add(StudentCondition.ZAPISANY);
                tempComboBox.getItems().add(StudentCondition.PRZEPISANY);
                tempComboBox.getItems().add(StudentCondition.WYPISANY);

            System.out.println(c.getCourse());
            tempComboBox.getSelectionModel().select(c.getCourseCondition());
            c.setCondition(tempComboBox);
        }
    }

    private Student initValues() {
        Course java = new Course("Java", 20);
        Course maths = new Course("Maths", 5);
        Course fem = new Course("FEM", 25);
        Student s1 = new Student("Szymon", "Rewilak", 2000, 401145, "srewilak@student.agh.edu.pl");
        s1.addCourse(java, StudentCondition.ZAPISANY);
        s1.addCourse(fem, StudentCondition.ZAPISANY);
        s1.addCourse(maths, StudentCondition.ZAPISANY);
        s1.addGrade(3.5f, "Kolokwium 1", "8-11-2021", java);
        s1.addGrade(5f, "Kolokwium 2", "8-12-2021", java);
        s1.addGrade(4f, "Kolokwium 3", "5-10-2021", java);
        s1.addGrade(2.0f, "Projekt 1", "2-11-2021", fem);
        s1.addGrade(3.0f, "Projekt 2", "8-10-2021", fem);
        s1.addGrade(2.0f, "Projekt 1", "2-11-2021", fem);
        s1.addGrade(4f, "Projekt 3", "23-10-2021", fem);
        s1.addGrade(4f, "Caleczki", "2-11-2021", maths);
        s1.addGrade(4.5f, "Aktywnosc", "6-11-2021", maths);
        Course python = new Course("Python", 15);
        Course physics = new Course("Physics", 18);
        for(int i = 0; i < 18; i++) {
            physics.addStudent(new Student("", "", 0, 0, ""));
        }
        List<StateChanger> tempList = new ArrayList<StateChanger>();
        tempList.add(new StateChanger(java, StudentCondition.ZAPISANY));
        tempList.add(new StateChanger(fem, StudentCondition.ZAPISANY));
        tempList.add(new StateChanger(maths, StudentCondition.ZAPISANY));
        tempList.add(new StateChanger(python, StudentCondition.NIEZAPISANY));
        tempList.add(new StateChanger(physics, StudentCondition.NIEZAPISANY));
        s1.setChanger(tempList);

        return s1;
    }
}