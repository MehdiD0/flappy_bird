import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pages/mainMenu.fxml"));
        primaryStage.setTitle("Flappy Bird");
        primaryStage.getIcons().add(new Image("ImageRessources/icon.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
