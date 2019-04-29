package com.example.demo.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {

    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
    private String userId;

    @NotBlank(groups = ValidGroup1.class)
    @Length(min= 4, max = 10, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
    private String password;

    @NotBlank(groups = ValidGroup1.class)
    private String userName;

    private boolean gender;
}
