package com.example.datathreadingapplication.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
