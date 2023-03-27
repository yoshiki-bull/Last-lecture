package com.udemy.videolist.application.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateForm {

    private final String MESSAGE = "入力してください";

    private final String LENGTH_MESSAGE = "100文字以内で入力してください";

    private int id;

    @NotBlank(message = MESSAGE)
    @Length(max = 100, message = LENGTH_MESSAGE)
    private String title;

    @NotBlank(message = MESSAGE)
    @Length(max = 100, message = LENGTH_MESSAGE)
    private String instructor;

    @NotBlank(message = MESSAGE)
    @Length(max = 100, message = LENGTH_MESSAGE)
    private String language;

    @NotNull(message = MESSAGE)
    private Boolean isFree;

    @NotNull(message = MESSAGE)
    @Pattern(regexp = "^\\d{0,9}+$", message = "数値を入力してください")
    private String price;
}
