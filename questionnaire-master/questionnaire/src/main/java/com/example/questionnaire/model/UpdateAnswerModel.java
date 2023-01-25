package com.example.questionnaire.model;

import javax.validation.constraints.*;
import java.util.List;

public class UpdateAnswerModel {
    @NotNull(message = "一つ選択してください")
    private Integer meat_id;

    @NotEmpty(message = "一つ以上選択してください")
    private List<Integer> vegetable_id;

    @NotBlank(message = "何か入力してください")
    @Size(max = 64, message = "最大64文字までです")
    @Pattern(regexp = "^[^ -~｡-ﾟ]+$", message = "全角文字のみで入力してくささい")
    private String idol_name;

    private Integer answer_id;

    public Integer getMeat_id() {
        return meat_id;
    }
    public List<Integer> getVegetable_id() {
        return vegetable_id;
    }
    public String getIdol_name() {
        return idol_name;
    }
    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setMeat_id(Integer meat_id) {
        this.meat_id = meat_id;
    }
    public void setVegetable_id(List<Integer> vegetable_id) {
        this.vegetable_id = vegetable_id;
    }
    public void setIdol_name(String idol_name) {
        this.idol_name = idol_name;
    }
    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }
}
