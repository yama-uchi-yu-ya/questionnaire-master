package com.example.questionnaire.entity;

public class UpdateVegetableAnswer {

    public int answer_id;
    public int vegetable_id;

    public int getAnswer_id() {
        return answer_id;
    }
    public int getVegetable_id() {
        return vegetable_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
    public void setVegetable_id(int vegetable_id) {
        this.vegetable_id = vegetable_id;
    }

    public UpdateVegetableAnswer() {
    }

    public UpdateVegetableAnswer(int answer_id, int vegetable_id) {
        this.answer_id = answer_id;
        this.vegetable_id = vegetable_id;
    }
}
