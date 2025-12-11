package ui.animations;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Zoom {

    public static void playTransition(Node outNode, Node inNode) {
        playAnimation(outNode, false, _ -> {        
            outNode.setVisible(false);
            inNode.setVisible(true);
            playAnimation(inNode, true, null);
        });
    }

    private static void playAnimation(Node node, boolean in, EventHandler<ActionEvent> onFinished) {
        int from = in ? 0 : 1;
        int to = in ? 1 : 0;

        if (in) {
            node.setScaleX(0);
            node.setScaleY(0);
        }
        
        ScaleTransition st = new ScaleTransition(Duration.millis(500), node);
        st.setFromX(from);
        st.setFromY(from);
        st.setToX(to);
        st.setToY(to);        
        st.setOnFinished(onFinished);

        st.play();
    }
}
