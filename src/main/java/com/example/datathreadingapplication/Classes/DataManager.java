package com.example.datathreadingapplication.Classes;

import com.example.datathreadingapplication.Controllers.DataController;
import com.example.datathreadingapplication.Controllers.TableViewController;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataManager implements Runnable{
    private String fileDataPath;
    private DataController controller;
    private int errors;
    public ArrayList<TableInstance> instances = new ArrayList<>();;

    public DataManager(String fileDataPath, DataController controller){
        this.fileDataPath = fileDataPath;
        this.controller = controller;
    }

    public void errorOccured(){
        ++errors;
        int finalErrors = errors;
        Platform.runLater(() ->
                controller.updateErrorCount(finalErrors)
        );
    }

    @Override
    public void run(){
        errors = 0;
        String line;
        String splitBy = ",";

        long totalLines, processed = 0;

        try {
            totalLines = Files.lines(Paths.get(fileDataPath)).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileDataPath))) {
            while ((line = br.readLine()) != null) {
                ++processed;
                String[] values = line.split(splitBy);
                int id;
                String name, last_name, email, gender, country, domain;
                LocalDate birth_date;

                try{
                    id = Integer.parseInt(values[0]);
                    name = values[1];
                    last_name = values[2];
                    email = values[3];
                    gender = values[4];
                    country = values[5];
                    domain = values[6];
                    birth_date = LocalDate.parse(values[7]);
                }catch (Exception e){
                    if(!values[0].equals("id")) {
                        errorOccured();
                    }

                    continue;
                }

                TableInstance instance = new TableInstance(id, name, last_name, email, gender, country, domain, birth_date);
                instances.add(instance);

                double percent = (processed * 100.0) / totalLines;

                Platform.runLater(() ->
                    controller.setProgress(String.format("%.2f%%", percent))
                );

                if(percent >= 100 && errors == 0){
                    Platform.runLater(() ->
                            controller.activateProceed()
                    );
                }

                Thread.sleep(1);
            }
        } catch (IOException e) {
            errorOccured();
        } catch (InterruptedException e) {
            errorOccured();
            throw new RuntimeException(e);
        }
    }
}
