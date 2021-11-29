package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("app-view.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        System.out.println("root = "+loader.getLocation().toString());

        MainSceneController controller = loader.getController();

        stage.setTitle("Szymon Rewilak - projekt 6");
        stage.setScene(new Scene(root, 500, 230));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}