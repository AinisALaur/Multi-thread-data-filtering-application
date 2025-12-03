package com.example.datathreadingapplication.Controllers;

import com.example.datathreadingapplication.Classes.TableInstance;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @FXML
    ChoiceBox<String> sortBy;

    @FXML
    ChoiceBox<String> sortingCriteria;

    @FXML
    DatePicker startDate;

    @FXML
    DatePicker endDate;

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        domainCol.setCellValueFactory(new PropertyValueFactory<>("domain"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        ArrayList<String> sortByInstances = new ArrayList<>(){
            {
                add("Id");
                add("First name");
                add("Last name");
                add("Email");
                add("Country");
                add("Gender");
                add("Domain");
                add("Birth date");
            }
        };

        ArrayList<String> sortByCriterias = new ArrayList<>(){
            {
                add("Ascending");
                add("Descending");
            }
        };

        sortBy.getItems().addAll(sortByInstances);
        sortingCriteria.getItems().addAll(sortByCriterias);
    }

    public void setSortByDate(){
        LocalDate startDateValue = startDate.getValue();
        LocalDate endDateValue = endDate.getValue();




    }

    public ArrayList<TableInstance> sortByChanged(ArrayList<TableInstance> data){
        String filterBy = sortBy.getValue();
        String filterCriteria = sortingCriteria.getValue();

        ArrayList<TableInstance> newInstances = null;

        switch (filterBy) {
            case "First name":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getFirst_name()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;

            case "Last name":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getLast_name()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;

            case "Email":
                newInstances = data.stream()
                    .sorted(Comparator.comparing(i -> i.getEmail()))
                    .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;

            case "Gender":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getGender()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;

            case "Country":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getCountry()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;

            case "Id":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getId()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;

            case "Domain":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getDomain()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;


            case "Birth date":
                newInstances = data.stream()
                        .sorted(Comparator.comparing(i -> i.getBirth_date()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if(filterCriteria.equals("Descending")){
                    Collections.reverse(newInstances);
                }

                break;
        }

        return newInstances;
    }

    public void prepareData(){
        LocalDate startDateValue = startDate.getValue();
        LocalDate endDateValue = endDate.getValue();

        ArrayList<TableInstance> filteredData = new ArrayList<>(instances);

        if (startDateValue != null) {
            filteredData = filteredData.stream()
                    .filter(i -> !i.getBirth_date().isBefore(startDateValue))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (endDateValue != null) {
            filteredData = filteredData.stream()
                    .filter(i -> !i.getBirth_date().isAfter(endDateValue))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if(sortBy.getValue() != null && sortingCriteria.getValue() != null){
            filteredData = sortByChanged(filteredData);
        }

        displayTable(filteredData);
    }

    public void removeFilters(){
        startDate.setValue(null);
        endDate.setValue(null);

        sortBy.setValue(null);
        sortingCriteria.setValue(null);

        displayTable(instances);
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
