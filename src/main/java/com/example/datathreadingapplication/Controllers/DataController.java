package com.example.datathreadingapplication.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class DataController {

    @FXML
    TextField selectedFilePath;

    @FXML
    VBox root;

    public void onFileSelect() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (selectedFile != null) {
            selectedFilePath.setText(selectedFile.getAbsolutePath());
        }
    }
}
