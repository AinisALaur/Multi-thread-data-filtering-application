package com.example.datathreadingapplication.Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataManager implements Runnable{
    private String fileDataPath;

    public DataManager(String fileDataPath){
        this.fileDataPath = fileDataPath;
    }

    @Override
    public void run(){
        String line;
        String splitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(fileDataPath))) {
            ArrayList<TableInstance> instances = new ArrayList<>();

            while ((line = br.readLine()) != null) {
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
                    continue;
                }

                TableInstance instance = new TableInstance(id, name, last_name, email, gender, country, domain, birth_date);
                instances.add(instance);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

}
