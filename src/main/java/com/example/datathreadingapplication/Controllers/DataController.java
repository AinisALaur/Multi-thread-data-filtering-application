package com.example.datathreadingapplication.Controllers;

import com.example.datathreadingapplication.Classes.DataManager;
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

    @FXML
    Button uploadBtn;

    public void onFileSelect() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (selectedFile != null) {
            selectedFilePath.setText(selectedFile.getAbsolutePath());
        }
    }

    public void uploadHandler() {
        String filePath = selectedFilePath.getText();
        uploadBtn.setDisable(true);
        if(filePath != null && !filePath.isEmpty()) {
            DataManager dataManager = new DataManager(filePath);
            Thread thread = new Thread(dataManager);
            thread.setDaemon(true);
            thread.start();
        }
    }
}
