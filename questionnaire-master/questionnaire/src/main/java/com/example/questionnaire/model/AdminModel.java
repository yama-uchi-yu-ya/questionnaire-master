package com.example.questionnaire.model;

import javax.validation.constraints.*;

public class AdminModel {

    @NotNull(message = "IDが違います")
    @Size(max = 16, min = 8, message = "IDが違います")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "IDが違います")
    private String name;
    
    @NotNull(message = "パスワードが違います")
    @Size(max = 16, min = 8, message = "パスワードが違います")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "パスワードが違います")
    private String password;

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

}
