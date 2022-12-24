package com.example.questionnaire.entity;

public class ViewVegetableAnswer {

    public int vegetable_id;
    public int answer_id;

    public int getVegetable_id() {
        return this.vegetable_id;
    }
    public int getAnswer_id() {
        return this.answer_id;
    }

    public void setVegetable_id(int vegetable_id) {
        this.vegetable_id = vegetable_id;
    }
    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public ViewVegetableAnswer() {
    }

    public ViewVegetableAnswer(int vegetable_id, int answer_id) {
        this.vegetable_id = vegetable_id;
        this.answer_id = answer_id;
    }
}
