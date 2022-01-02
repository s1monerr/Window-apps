package Controller;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.nio.file.ClosedFileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChangeInfoController implements Initializable {
    @FXML
    private Button saveChangesButton;

    @FXML
    private TableView<StateChanger> courseInfo;

    @FXML
    private TableColumn<StateChanger, ComboBox<StudentCondition>> condition;

    @FXML
    private TableColumn<StateChanger, String> course;

    @FXML
    private Tooltip tooltip;

    private List<StateChanger> stateChangerList;

    private void addTooltipToColumnCells(TableColumn<StateChanger, String> column){
        Callback<TableColumn<StateChanger, String>, TableCell<StateChanger, String>> existingCellFactory
                = column.getCellFactory();

        column.setCellFactory(c -> {
            TableCell<StateChanger, String> cell = existingCellFactory.call(c);

            Tooltip tooltip = new Tooltip();
            tooltip.prefWidthProperty().bind(cell.widthProperty());
            tooltip.textProperty().bind(cell.itemProperty().asString());

            cell.setTooltip(tooltip);
            return cell ;
        });
    }

    public boolean getCSVFLag(){
        return csvFlag;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csvFlag = false;
        Platform.runLater(()->{
            course.setCellValueFactory(new PropertyValueFactory<StateChanger, String>("course"));

            condition.setCellValueFactory(new PropertyValueFactory<StateChanger, ComboBox<StudentCondition>>("condition"));
            courseInfo.setItems(getChangerList(stateChangerList));
            addTooltipToColumnCells(course);
        });

    }

    public void setStateChangerList(List<StateChanger> stateChangerList) {
        this.stateChangerList = stateChangerList;
    }

    private ObservableList<StateChanger> getChangerList(List<StateChanger> list){
        return FXCollections.observableArrayList(list);
    }

    private Student user;

    public void setUser(Student user) {
        this.user = user;
    }

    private static boolean changeFlag;

    private static boolean errorFlag;

    private static boolean csvFlag;

    @FXML
    private Label errorLabel;

    public void saveButtonHandle(ActionEvent actionEvent) throws IOException {
        changeFlag = false;
        errorFlag = false;
        System.out.println("saves changed");
        for(StateChanger s : courseInfo.getItems()){
            // jesli ustawione zapisany, a nie byl zapisany
            if(s.getCondition().getValue() == StudentCondition.ZAPISANY) {
                if (!(user.getCourseList().contains(s.getCourseRoot()))) {
                    if(!(s.getCourseRoot().checkEmptyPlaces())) {   // sprawdzenie czy w grupie sa wolne miejsca
                        errorFlag = true;
                        errorLabel.setText("Error: group is full! ["+s.getCourseRoot().getEnrolledStudents()+"/"+s.getCourseRoot().studentsLimit+"]");
                        System.err.println("Error: group is full!");
                        s.getCondition().getSelectionModel().select(2);
                    }

                    if(!errorFlag){
                        user.addCourse(s.getCourseRoot(), StudentCondition.OCZEKUJACY);
                        changeFlag = true;
                    }
                }
            }

            else if(user.getChangerByCourse(s.getCourseRoot()).getCourseCondition() != s.getCondition().getValue()){
                System.out.println("changing "+s.getCourseRoot().getName()+" cond to "+s.getCondition().getValue());
                user.setCourseCondition(s.getCourseRoot(), s.getCondition().getValue());
                user.setChangerStudentCondition(s.getCourseRoot(), s.getCondition().getValue());
                changeFlag = true;
            }
        }
        if(!errorFlag) {
            Stage stage = (Stage) saveChangesButton.getScene().getWindow();
            stage.close();
        }
    }

    public boolean getChangeFlag(){
        return changeFlag;
    }

    /** CSV file importing
     *
     */

    public void importCSVbuttonHandle(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        fileChooser.setTitle("Choose CSV file to import");
        Stage stage = (Stage) saveChangesButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);


        // saving state
        List<Course> courseList = new ArrayList<Course>();
        List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
        List<StateChanger> stateChangerList = new ArrayList<StateChanger>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine())!= null){
                String[] row = line.split(",");
                Course course = new Course(row[0], 20);
                CourseInfo courseInfo = new CourseInfo(course, StudentCondition.OCZEKUJACY, Float.parseFloat(row[1]));
                StateChanger stateChanger = new StateChanger(course, StudentCondition.OCZEKUJACY);
                courseList.add(course);
                courseInfoList.add(courseInfo);
                stateChangerList.add(stateChanger);
            }

            user.setCourseList(courseList);
            user.setCourseInfoList(courseInfoList);
            for(StateChanger c : stateChangerList){
                ComboBox<StudentCondition> tempComboBox = new ComboBox<StudentCondition>();
                StudentCondition condition = c.getCourseCondition();
                tempComboBox.getItems().add(StudentCondition.ZAPISANY);
                tempComboBox.getItems().add(StudentCondition.PRZEPISANY);
                tempComboBox.getItems().add(StudentCondition.WYPISANY);

                System.out.println(c.getCourse());
                tempComboBox.getSelectionModel().select(c.getCourseCondition());
                c.setCondition(tempComboBox);
            }

            user.setChanger(stateChangerList);
            csvFlag = true;
            stage.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
