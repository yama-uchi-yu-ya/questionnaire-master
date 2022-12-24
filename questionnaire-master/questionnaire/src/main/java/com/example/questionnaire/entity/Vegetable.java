package com.example.questionnaire.entity;

public class Vegetable {
    public int vegetable_id;
    public String name;

    public int getVegetableId() {
        return this.vegetable_id;
    }

    public void setVegetableId(int vegetable_id) {
        this.vegetable_id = vegetable_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vegetable() {
    }
    public Vegetable(int vegetable_id, String name) {
        this.vegetable_id = vegetable_id;
        this.name = name;
    }
}
