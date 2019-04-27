package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.model.ArticleForm;
import com.example.demo.login.domain.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articleDetail/{id}")
    public String getArticleDetail(Model model, @PathVariable("id") Long articleId){
      System.out.println(articleId);

      model.addAttribute("contents", "login/articleDetail :: articleDetail_contents");

      if (articleId != null){
        Article article = articleService.selectOne(articleId);

        model.addAttribute("article", article);
      }

      return "login/homeLayout";
    }

    @GetMapping("/articleEdit/{id}")
    public String getArticleEdit(@ModelAttribute ArticleForm form, Model model, @PathVariable("id") Long articleId) {
        System.out.println(articleId);

        model.addAttribute("contents", "login/articleEdit :: articleEdit_contents");

        if (articleId != null) {
            Article article = articleService.selectOne(articleId);

            form.setId(articleId);
            form.setUserId(article.getUserId());
            form.setTitle(article.getTitle());
            form.setContent(article.getContent());
        }

        return "login/homeLayout";
    }
}