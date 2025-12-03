package com.example.datathreadingapplication.Controllers;

import com.example.datathreadingapplication.Classes.TableInstance;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class TableViewController {
    @FXML
    private TableView<TableInstance> tableView;
    @FXML
    private TableColumn<TableInstance, Integer> idCol;
    @FXML
    private TableColumn<TableInstance, String> firstNameCol;
    @FXML
    private TableColumn<TableInstance, String> lastNameCol;
    @FXML
    private TableColumn<TableInstance, String> emailCol;
    @FXML
    private TableColumn<TableInstance, String> genderCol;
    @FXML
    private TableColumn<TableInstance, String> countryCol;
    @FXML
    private TableColumn<TableInstance, String> domainCol;
    @FXML
    private TableColumn<TableInstance, LocalDate> birthDateCol;

    private ArrayList<TableInstance> instances;

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        domainCol.setCellValueFactory(new PropertyValueFactory<>("domain"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
    }

    public void displayTable(ArrayList<TableInstance> instancesToDisplay) {
        tableView.getItems().setAll(instancesToDisplay);
    }

    public void setInstanceArray(ArrayList<TableInstance> instances){
        this.instances = instances;
        if (tableView != null && instances != null) {
            displayTable(instances);
        }
    }
}
