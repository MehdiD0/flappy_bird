package Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Pipe extends StackPane {
    public static final int PIPE_WIDTH = 50;  // Width of the pipe
    public int PIPE_HEIGHT;

    private Rectangle pipeShape;  // The pipe's visual representation
    private ImageView backgroundImageView;  // ImageView for the background image

    // Constructor with an optional background image
    public Pipe(int x, int y, int h) {
        this.PIPE_HEIGHT = h;

        // Create the pipe's rectangular shape
        pipeShape = new Rectangle(PIPE_WIDTH, PIPE_HEIGHT);
        pipeShape.setOpacity(0);

        // Create and set up the background image
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(PIPE_WIDTH);
        backgroundImageView.setFitHeight(PIPE_HEIGHT);
        backgroundImageView.setPreserveRatio(false); // Stretch image to fit the pipe size

        // Position the pipe using layout coordinates
        setLayoutX(x);
        setLayoutY(y);

        // Add both background image and pipe shape to the StackPane
        getChildren().addAll(backgroundImageView, pipeShape);
    }

    // Method to set the background image
    public void setBackgroundImage(String imagePath) {
        Image backgroundImage = new Image(imagePath);
        backgroundImageView.setImage(backgroundImage);
    }

    // Move the pipe to the left (simulating the bird flying past)
    public void moveLeft(double distance) {
        setLayoutX(getLayoutX() - distance);
    }

    // Update the height of the pipe and adjust its position
    public void updateHeight(int newHeight) {
        this.PIPE_HEIGHT = newHeight;
        pipeShape.setHeight(newHeight);  // Adjust the pipe shape's height
        backgroundImageView.setFitHeight(newHeight);  // Adjust the background image size
    }

    // Set the Y position of the pipe
    public void setY(double y) {
        setLayoutY(y);  // Replace setY() of Rectangle with setLayoutY() of Pane
    }

    // Set the X position of the pipe
    public void setX(double x) {
        setLayoutX(x);  // Replace setX() of Rectangle with setLayoutX() of Pane
    }

    // Get the current X position of the pipe
    public double getX() {
        return getLayoutX();  // Replace getX() of Rectangle with getLayoutX() of Pane
    }

    // Get the current Y position of the pipe
    public double getY() {
        return getLayoutY();  // Replace getY() of Rectangle with getLayoutY() of Pane
    }
}
