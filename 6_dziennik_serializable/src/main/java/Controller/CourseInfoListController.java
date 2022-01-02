package Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseInfoListController implements Initializable {

    @FXML
    private TableView<CourseInfo> courseInfoTable;

    @FXML
    private TableColumn<CourseInfo, String> course;

    @FXML
    private TableColumn<CourseInfo, Float> average;

    @FXML
    private TableColumn<CourseInfo, StudentCondition> condition;

    private List<CourseInfo> courseInfoList;

    public void setCourseInfoTable(List<CourseInfo> list){
        this.courseInfoList = list;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            course.setCellValueFactory(new PropertyValueFactory<CourseInfo, String>("course"));
            average.setCellValueFactory(new PropertyValueFactory<CourseInfo, Float>("average"));
            condition.setCellValueFactory(new PropertyValueFactory<CourseInfo, StudentCondition>("condition"));
            courseInfoTable.setPlaceholder(new Label("No courses to show..."));
            courseInfoTable.setItems(getCourseInfoList(courseInfoList));
        });
    }

    private ObservableList<CourseInfo> getCourseInfoList(List<CourseInfo> courseInfoList){
        return FXCollections.observableArrayList(courseInfoList);
    }

}
