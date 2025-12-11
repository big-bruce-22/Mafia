package ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.player.PrimaryRole;
import game.player.SecondaryRole;
import game.player.Side;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import util.ResourcesLoader;

@RequiredArgsConstructor
public class LobbyCreationPanelController implements Initializable {

    private final Stage stage;

    @FXML
    private GridPane rootPane, boardGridPane;

    @FXML
    private TextField playerCounterField;

    @FXML
    private MFXButton minusPlayerButton, addPlayerButton,
        classicPresetButton, specialPresetButton, extremePresetButton,
        leftArrowButton, rightArrowButton,
        closeButton, createButton;

    @FXML
    private ImageView roleImageView;

    @FXML
    private StackPane roleStackPane;

    @FXML
    private FlowPane goodRolesFlowPane, evilRolesFlowPane, secondaryRolesFlowPane;

    private int currentView = 0;

    private final Image GOOD_ROLE_IMAGE_VIEW = new Image("images/roles_category/Good.png"),
        EVIL_ROLE_IMAGE_VIEW = new Image("images/roles_category/Evil.png"),
        SECONDARY_ROLE_IMAGE_VIEW = new Image("images/roles_category/Secondary.png");

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addCivilianCounter();
        addGoodRoles();
        addEvilRoles();
        addSecondaryRoles();

        roleImageView.setImage(GOOD_ROLE_IMAGE_VIEW);

        goodRolesFlowPane.setVisible(true);
        goodRolesFlowPane.toFront();

        evilRolesFlowPane.setVisible(false);
        leftArrowButton.setVisible(false);

        leftArrowButton.setOnAction(_ -> leftArrowAction());
        rightArrowButton.setOnAction(_ -> rightArrowAction());
    }

    public void closeAction(Runnable r) {
        closeButton.setOnAction(_ -> r.run());
    }

    public void createAction(Runnable r) {
        createButton.setOnAction(_ -> r.run());
    }

    private void addCivilianCounter() {
        var pane = createRoleCounterPanel(PrimaryRole.CIVILIAN);
        pane.setScaleX(0.8);
        pane.setScaleY(0.8);
        boardGridPane.add(pane, 2, 0);
    }

    private void leftArrowAction() {
        switch (currentView) {
            case 0 -> {
                return;
            }
            case 1 -> {
                evilRolesFlowPane.setVisible(false);
                goodRolesFlowPane.setVisible(true);
                roleImageView.setImage(GOOD_ROLE_IMAGE_VIEW);
                leftArrowButton.setVisible(false);
                currentView--;
            }
            case 2 -> {
                secondaryRolesFlowPane.setVisible(false);
                evilRolesFlowPane.setVisible(true);
                roleImageView.setImage(EVIL_ROLE_IMAGE_VIEW);
                rightArrowButton.setVisible(true);
                currentView--;
            }
        }
    }

    private void rightArrowAction() {
        switch (currentView) {
            case 0 -> {
                goodRolesFlowPane.setVisible(false);
                evilRolesFlowPane.setVisible(true);
                roleImageView.setImage(EVIL_ROLE_IMAGE_VIEW);
                leftArrowButton.setVisible(true);
                currentView++;
            }
            case 1 -> {
                evilRolesFlowPane.setVisible(false);
                secondaryRolesFlowPane.setVisible(true);
                roleImageView.setImage(SECONDARY_ROLE_IMAGE_VIEW);
                rightArrowButton.setVisible(false);
                currentView++;
            }
            case 2 -> {
                return;
            }
        }
    }

    private void addGoodRoles() {
        for (var role : PrimaryRole.getAll(r -> r.side() == Side.GOOD && r != PrimaryRole.CIVILIAN)) {
            goodRolesFlowPane.getChildren().add(createRoleCounterPanel(role));
        }
    }

    private void addEvilRoles() {
        for (var role : PrimaryRole.getAll(r -> r.side() == Side.EVIL)) {
            evilRolesFlowPane.getChildren().add(createRoleCounterPanel(role));
        }
    }
    
    private void addSecondaryRoles() {
        VBox vbox = new VBox();
        
        try {
            FXMLLoader loader = new FXMLLoader(ResourcesLoader.loadURL("/fxml/RoleCounterPanel.fxml"));
            loader.setControllerFactory(_ -> new RoleCounterPanelController());
            GridPane pane = loader.load();
            var controller = (RoleCounterPanelController) loader.getController();
    
            controller.setTokenImage(SecondaryRole.SOULMATE.tokenPath());
            controller.setRole(SecondaryRole.SOULMATE.roleName(), null);

            vbox.setId("vbox");
            vbox.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        secondaryRolesFlowPane.getChildren().add(vbox);
    }

    private GridPane createRoleCounterPanel(PrimaryRole role) {
        try {
            FXMLLoader loader = new FXMLLoader(ResourcesLoader.loadURL("/fxml/RoleCounterPanel.fxml"));
            loader.setControllerFactory(_ -> new RoleCounterPanelController());

            GridPane pane = loader.load();

            var controller = (RoleCounterPanelController) loader.getController();

            controller.setTokenImage(role.tokenPath());
            controller.setRole(role.roleName(), null);

            if (role == PrimaryRole.CIVILIAN) {
                controller.setCounterFieldEditable(false);   
                controller.setVisibleButtons(false);
            }

            if (role == PrimaryRole.GODFATHER) {
                controller.setMaximumRole(1);
            }

            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
