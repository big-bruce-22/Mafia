package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;

public class RoleCounterPanelController implements Initializable {

    @FXML 
    private GridPane rootPane;

    @FXML 
    private MFXButton addButton, minusButton;

    @FXML 
    private ImageView addImageView, minusImageView, tokenImage;

    @FXML 
    private TextField counterField;

    @FXML 
    private Label roleLabel, descriptionLabel;

    private ObjectProperty<Integer> countProperty = new SimpleObjectProperty<>(0);
    private IntegerProperty maximumRole = new SimpleIntegerProperty(-1);

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bindCounterField();


        minusButton.setOnAction(_ -> minusAction());
        addButton.setOnAction(_ -> plusAction());
    }

    public void setMaximumRole(int i) {
        maximumRole.set(i);
    }

    private void bindCounterField() {
        TextFormatter<Integer> formatter = new TextFormatter<>(
            new IntegerStringConverter(),
            0,
            change -> change.getControlNewText().matches("-?\\d*") ? change : null
        );

        counterField.setTextFormatter(formatter);
        counterField.textProperty().bindBidirectional(countProperty, new IntegerStringConverter());
    }

    private void minusAction() {
        if (countProperty.get() >= 1) {
            countProperty.set(countProperty.get() - 1);
        } else {
            return;
        }

        if (countProperty.get() == 0) {
            minusImageView.setImage(new Image("/images/components/buttons/Minus_Gray.png"));
            minusButton.setDisable(true);
        }

        if (countProperty.get() < maximumRole.get()) {
            addImageView.setImage(new Image("/images/components/buttons/Plus.png"));
            addButton.setDisable(false);
        }
    }

    private void plusAction() {
        if (maximumRole.get() < 1) {
            return;
        }
        countProperty.set(countProperty.get() + 1);

        if (countProperty.get() == 1) {
            minusImageView.setImage(new Image("/images/components/buttons/Minus.png"));
            minusButton.setDisable(false);
        }

        if (countProperty.get() == maximumRole.get()) {
            addImageView.setImage(new Image("/images/components/buttons/Plus_Gray.png"));
            addButton.setDisable(true);
        }
    }

    public void setTokenImage(String imagePath) {
        tokenImage.setImage(new Image(imagePath));
    }

    public void setRole(String role, String description) {
        roleLabel.setText(role);
        descriptionLabel.setText(description == null ? "" : description);
    }

    public int getCount() {
        return countProperty.get();
    }

    public void setVisibleButtons(boolean b) {
        addButton.setVisible(b);
        minusButton.setVisible(b);
    }

    public void setCounterFieldEditable(boolean b) {
        counterField.setEditable(b);
        counterField.setFocusTraversable(b);
    }

    public void setCouner(int i) {
        countProperty.set(i);
    }
}
