package com.example.questionnaire.entity;

public class Meat {
    public int meat_id;
    public String name;

    public int getMeatId() {
        return this.meat_id;
    }

    public void setMeatId(int meat_id) {
        this.meat_id = meat_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meat() {
    }
    public Meat(int meat_id, String name) {
        this.meat_id = meat_id;
        this.name = name;
    }
}
