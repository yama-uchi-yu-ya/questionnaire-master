package com.example.questionnaire.entity;

public class Admin {

    public String name;
    public String password;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
