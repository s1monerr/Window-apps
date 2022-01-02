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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseTableController implements Initializable {

    // TABELA OCEN
    @FXML
    private TableView<Grade> courseTable;
    @FXML
    private TableColumn<Grade, Float> grade;
    @FXML
    private TableColumn<Grade, String> name;
    @FXML
    private TableColumn<Grade, String> date;
    @FXML
    private Label successLabel;

    private String filename;

    @FXML
    public void sortCourseTable(){
        System.out.println("sorting...");
    }

    private List<Grade> gradeList;

    public void setGradeList(List<Grade> list){
        this.gradeList = list;
    }

    public void setFileName(String s) {
        this.filename = s;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseTable.setPlaceholder(new Label("Grades list is empty."));
        Platform.runLater(()->{
            name.setCellValueFactory(new PropertyValueFactory<Grade, String>("name"));
            grade.setCellValueFactory(new PropertyValueFactory<Grade, Float>("grade"));
            date.setCellValueFactory(new PropertyValueFactory<Grade, String>("date"));
            courseTable.setItems(getGradeList(gradeList));
        });

    }

    public ObservableList<Grade> getGradeList(List<Grade> list){
        return FXCollections.observableArrayList(list);
    }

    /**
     *  SAVING FILE TO .csv
     * */

    public void exportCSV(String filename){
        File csvFile = new File(filename);
        try {
            PrintWriter writer = new PrintWriter(csvFile);
            for(Grade g : gradeList){
                writer.printf("%s, %.2f, %s\n", g.getName(), g.getGrade(), g.getDate());
            }
            writer.close();
            successLabel.setText("CSV imported successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportCSVButtonHandle(){
        exportCSV(filename);
    }


}
