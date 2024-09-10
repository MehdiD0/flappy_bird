package Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bird extends Pane {
    private ImageView birdImageView;

    public Bird(int x, int y, int w, int h) {
        // Create the ImageView and set the image
        birdImageView = new ImageView();
        Image image = new Image("ImageRessources/icon.png"); // Update the path to your image
        birdImageView.setImage(image);
        birdImageView.setFitWidth(w);
        birdImageView.setFitHeight(h);
        birdImageView.setPreserveRatio(true); // Preserve the aspect ratio of the image

        // Set the position of the bird using the Pane's layout properties
        setLayoutX(x);
        setLayoutY(y);

        // Add the ImageView to the Pane
        getChildren().add(birdImageView);
    }

    // Set the X position of the bird
    public void setX(double x) {
        setLayoutX(x);  // Use setLayoutX of Pane instead of setX of ImageView
    }

    // Set the Y position of the bird
    public void setY(double y) {
        setLayoutY(y);  // Use setLayoutY of Pane instead of setY of ImageView
    }

    // Get the X position of the bird
    public double getX() {
        return getLayoutX();  // Use getLayoutX of Pane instead of getX of ImageView
    }

    // Get the Y position of the bird
    public double getY() {
        return getLayoutY();  // Use getLayoutY of Pane instead of getY of ImageView
    }
}
