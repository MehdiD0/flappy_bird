package Utils;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

public class CollisionUtil {

    // Method to check if two Panes are colliding
    public static boolean isColliding(Pane pane1, Pane pane2) {
        // Get the bounds of the panes in the parent coordinate system
        Bounds boundsPane1 = pane1.getBoundsInParent();
        Bounds boundsPane2 = pane2.getBoundsInParent();

        // Check if the bounds of the two panes intersect
        return boundsPane1.intersects(boundsPane2);
    }
}

