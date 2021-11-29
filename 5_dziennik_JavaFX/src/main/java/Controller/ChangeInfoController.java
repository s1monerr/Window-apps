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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.ClosedFileSystemException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

}
