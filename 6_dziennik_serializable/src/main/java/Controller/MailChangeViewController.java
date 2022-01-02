package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MailChangeViewController implements Initializable {

    private Student user;

    @FXML
    private TextField mailInput;

    @FXML
    private Label errorLabel;

    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()-> {
            mailInput.setText(user.getEmail());
        });
    }

    public void saveButtonHandle(){
        String newEmail = mailInput.getText();
        // error control
        if(newEmail.equals(user.getEmail())) {
            errorLabel.setText("Error: \nemail cannot be the same as current!");
        }

        else if(!(newEmail.contains("@"))){
            errorLabel.setText("Error: \ninvalid email address");
        }

        else if(!(newEmail.contains(".pl")||newEmail.contains(".com"))){
            errorLabel.setText("Error: \ninvalid email address");
        }

        else{
            user.setEmail(new Email(newEmail));
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }

    }

    public void setUser(Student user) {
        this.user = user;
    }
}
