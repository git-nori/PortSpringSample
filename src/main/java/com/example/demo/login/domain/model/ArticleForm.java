package com.example.demo.login.domain.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ArticleForm {

    private Long id;
    private String userId;
    private String userName;
    @NotBlank
    @Length(min=1, max=30)
    private String title;
    private String content;
}
