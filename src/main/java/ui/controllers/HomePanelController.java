package ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.RequiredArgsConstructor;
import ui.animations.Fade;
import util.ResourcesLoader;

@RequiredArgsConstructor
public class HomePanelController implements Initializable {

    private final Stage stage;

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView greenView, redDividerView, polkaDotsView, handImageView;

    @FXML
    private MFXButton joinButton, profileButton, createButton, settingsButton;

    @FXML
    private VBox leftVBox, rightVBox;

    private GridPane lobbyCreationPanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(ResourcesLoader.loadURL("/fxml/LobbyCreationPanel.fxml"));
            loader.setControllerFactory(_ -> new LobbyCreationPanelController(stage));

            lobbyCreationPanel = loader.load();

            rootPane.getChildren().add(lobbyCreationPanel);

            ((LobbyCreationPanelController) loader.getController()).closeAction(() -> {
                Fade.playAnimation(lobbyCreationPanel, false);
                playRedDividerAnimation(true, null);
                playButtonAnimation(true, _ -> lobbyCreationPanel.toBack());
            });
            lobbyCreationPanel.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playHandAnimation();
        playPolkaDotsAnimation();

        createButton.setOnAction(_ -> {
            playRedDividerAnimation(false, null);
            playButtonAnimation(false, _ -> {
                lobbyCreationPanel.toFront();
                Fade.playAnimation(lobbyCreationPanel, true);
            });
        });
    }

    private void playRedDividerAnimation(boolean in, EventHandler<ActionEvent> onFinished) {
        var fromX = in ? -stage.getWidth() : 0;
        var toX = in ? 0 : -stage.getWidth();

        if (in) {
            playHandAnimation();
        }

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1), redDividerView);
        tt1.setFromX(fromX);
        tt1.setToX(toX);
        tt1.setInterpolator(Interpolator.LINEAR);
        
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1), handImageView);
        tt2.setFromX(fromX);
        tt2.setToX(toX);
        tt2.setInterpolator(Interpolator.LINEAR);

        tt2.setOnFinished(onFinished);

        tt1.play();
        tt2.play();
    }

    private void playButtonAnimation(boolean in, EventHandler<ActionEvent> onFinished) {
        var fromX1 = in ? -stage.getWidth() : 0;
        var toX1 = in ? 0 : -stage.getWidth();

        var fromX2 = in ? stage.getWidth() : 0;
        var toX2 = in ? 0 : stage.getWidth();

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1), leftVBox);
        tt1.setFromX(fromX1);
        tt1.setToX(toX1);
        tt1.setInterpolator(Interpolator.LINEAR);
        
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1), rightVBox);
        tt2.setFromX(fromX2);
        tt2.setToX(toX2);
        tt2.setInterpolator(Interpolator.LINEAR);

        tt2.setOnFinished(onFinished);

        tt1.play();
        tt2.play();
    }

    private void playHandAnimation() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), handImageView);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.95);
        scaleTransition.setToY(0.95);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        
        scaleTransition.play();
    }

    private void playPolkaDotsAnimation() {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(5), polkaDotsView);
        tt.setFromY(stage.getHeight());
        tt.setToY(-100);
        tt.setInterpolator(Interpolator.LINEAR);
        tt.setCycleCount(TranslateTransition.INDEFINITE);

        tt.setOnFinished(e -> {
            polkaDotsView.setTranslateY(0);
            polkaDotsView.setLayoutY(stage.getHeight());
        });

        tt.play();
    }
}
