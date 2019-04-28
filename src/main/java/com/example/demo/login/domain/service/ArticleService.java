package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.repository.ArticleDao;

@Transactional
@Service
public class ArticleService {

    @Autowired
    ArticleDao dao;

    public boolean insert(Article article) {

        int rowNumber = dao.insertOne(article);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public int count() {
        return dao.count();
    }

    public List<Article> selectMany(){
        return dao.selectMany();
    }

    public Article selectOne(Long id) {
        return dao.selectOne(id);
    }

    public boolean updateOne(Article article) {

        int rowNumber = dao.updateOne(article);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public boolean delete(Long id) {

        int rowNumber = dao.deleteOne(id);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public Article selectDistinctUserIdByUserId(String userId) {
        return dao.selectDistinctUserIdByUserId(userId);
    }
}
