package Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MyInfoViewController implements Initializable {

    private Student user;

    @FXML
    TableView<Student> infoTable;

    @FXML
    TableColumn<Student, String> name;

    @FXML
    TableColumn<Student, String> surname;

    @FXML
    TableColumn<Student, Integer> index;

    @FXML
    TableColumn<Student, String> email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()-> {
            name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
            surname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
            index.setCellValueFactory(new PropertyValueFactory<Student, Integer>("index"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
            infoTable.setSelectionModel(null);
            infoTable.setItems(FXCollections.observableArrayList(user));
        });
    }

    public void setUser(Student user) {
        this.user = user;
    }
}
