package com.example.questionnaire.model;

import javax.validation.constraints.*;
import java.util.List;

public class UpdateAnswerModel {
    @NotNull(message = "一つ選択してください")
    private Integer like_meat;

    @NotEmpty(message = "一つ以上選択してください")
    private List<Integer> like_veg;

    @NotBlank(message = "何か入力してください")
    @Size(max = 64, message = "最大64文字までです")
    @Pattern(regexp = "^[^ -~｡-ﾟ]+$", message = "全角文字のみで入力してくささい")
    private String like_idol;

    public Integer getLike_meat() {
        return this.like_meat;
    }

    public void setLike_meat(Integer like_meat) {
        this.like_meat = like_meat;
    }

    public List<Integer> getLike_veg() {
        return this.like_veg;
    }

    public void setLike_veg(List like_veg) {
        this.like_veg = like_veg;
    }

    public String getLike_idol() {
        return this.like_idol;
    }

    public void setLike_idol(String like_idol) {
        this.like_idol = like_idol;
    }
}
