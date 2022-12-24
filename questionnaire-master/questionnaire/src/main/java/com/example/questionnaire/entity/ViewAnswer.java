package com.example.questionnaire.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answers")
public class ViewAnswer {
    @Id
    public int answer_id;
    @Column
    public int meat_id;
    @Column
    public String idol_name;
    @Transient
    public List<Integer> vegetable_id;

    public int getAnswer_id() {
        return this.answer_id;
    }
    public int getMeat_id() {
        return this.meat_id;
    }
    public String getIdol_name() {
        return this.idol_name;
    }
    public List<Integer> getVegetable_id() {
        return this.vegetable_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
    public void setMeat_id(int meat_id) {
        this.meat_id = meat_id;
    }
    public void setIdol_name(String idol_name) {
        this.idol_name = idol_name;
    }
    public void setVegetable_id(List<Integer> vegetable_id) {
        this.vegetable_id = vegetable_id;
    }

    public ViewAnswer() {
    }

    public ViewAnswer(int answer_id, int meat_id, String idol_name, List<Integer> vegetable_id) {
        this.answer_id = answer_id;
        this.meat_id = meat_id;
        this.idol_name = idol_name;
        this.vegetable_id = vegetable_id;
    }
}
