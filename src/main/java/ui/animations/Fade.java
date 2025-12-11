package ui.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {

    public static void playAnimation(Node node, boolean in) {
        var startOpacity = in ? 0 : 1;
        var endOpacity = in ? 1 : 0;

        node.setOpacity(startOpacity);

        FadeTransition ft = new FadeTransition(Duration.seconds(1), node);
        ft.setToValue(endOpacity);
        ft.play();
    }
    
}
