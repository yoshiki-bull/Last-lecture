package com.udemy.videolist.application.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateForm {
    private int id;

    @NotBlank(message = "{form.notblank}")
    @Length(max = 100, message = "{form.title.length}")
    private String title;

    @NotBlank(message = "{form.notblank}")
    @Length(max = 100, message = "{form.instructor.length}")
    private String instructor;

    @NotBlank(message = "{form.notblank}")
    @Length(max = 100, message = "{form.language.length}")
    private String language;

    @NotNull(message = "{form.notnull}")
    private Boolean isFree;

    @NotBlank(message = "{form.notblank}")
    @Pattern(regexp = "^\\d{0,9}+$", message = "{form.price.pattern}")
    private String price;
}
