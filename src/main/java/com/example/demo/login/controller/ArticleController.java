package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.model.ArticleForm;
import com.example.demo.login.domain.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articleNew")
    public String getArticleNew(@ModelAttribute ArticleForm form, Model model, Authentication authentication){
        System.out.println("記事新規作成");

        String UserId = authentication.getName();

        model.addAttribute("contents", "login/articleNew :: article_new_contents");

        if (UserId != null && UserId.length() > 0){
            Article article = articleService.selectOneByUserId(UserId);

            form.setUserId(article.getUserId());

            model.addAttribute("articleForm", form);
        }

        return "login/homeLayout";
    }

    @PostMapping("/articleNew")
    public String postArticleNew(@ModelAttribute ArticleForm form, Authentication authentication, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return getArticleNew(form, model, authentication);
        }

        Article article = new Article();

        article.setUserId(form.getUserId());
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());

        boolean result = articleService.insert(article);

        if (result == true){
            model.addAttribute("result", "更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
        }

        return "forward:/home";
    }

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


    @PostMapping(value="/articleEdit", params="update")
    public String postArticleEditUpate(ArticleForm form, Model model) {
        System.out.println("記事更新処理");

        Article article = new Article();

        article.setId(form.getId());
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());

        try {
            boolean result = articleService.updateOne(article);

            if (result == true) {
                model.addAttribute("result", "更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
            }
        } catch(DataAccessException e) {
            model.addAttribute("result", "更新失敗");
        }

        return getArticleDetail(model, form.getId());
    }

    @PostMapping(value="/articleEdit", params="delete")
    public String postArticleEditDelete(@ModelAttribute ArticleForm form, Model model) {
        System.out.println("記事削除処理");

        boolean result = articleService.delete(form.getId());

        if (result == true) {
            model.addAttribute("result", "記事削除成功");
        } else {
            model.addAttribute("result", "記事削除失敗");
        }

        return "forward:/home";
    }
}