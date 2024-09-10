package pages;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import javafx.application.Platform;

public class MainMenu {

    @FXML
    Button startButton;
    @FXML
    Button optionsButton;
    @FXML
    Button quitButton;

    @FXML
    public void initialize() {
        // Set action for the Start button
        startButton.setOnAction(event -> startGame());

        // Set action for the Options button
        optionsButton.setOnAction(event -> openOptions());

        // Set action for the Quit button
        quitButton.setOnAction(event -> quitApplication());
    }

    private void startGame() {
        try {
            // Load the new FXML file (replace "Game.fxml" with your actual FXML file name)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent gameRoot = loader.load();
    
            // Get the current stage and set the new scene
            Stage currentStage = (Stage) startButton.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            currentStage.setScene(gameScene);
            currentStage.setResizable(false);
    
            // Center the stage on the screen
            currentStage.centerOnScreen();
    
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the game scene.");
        }
    }

    private void openOptions() {
        // open options/settings
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Options");
        alert.setHeaderText(null);
        alert.setContentText("Options menu is not implemented yet.");
        alert.showAndWait();
    }

    private void quitApplication() {
        // quit the application
        Platform.exit();
    }
}