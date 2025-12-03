package com.example.datathreadingapplication.Classes;

import java.time.LocalDate;

public class TableInstance {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String country;
    private String domain;
    private LocalDate birth_date;

    public TableInstance(int id, String first_name, String last_name, String email, String gender, String country,
                         String domain, LocalDate birth_date) {

        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.domain = domain;
        this.birth_date = birth_date;
    }

}
