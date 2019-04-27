package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Article;

public interface ArticleDao {

    public int count() throws DataAccessException;

    public int insertOne(Article article) throws DataAccessException;

    public Article selectOne(Long id) throws DataAccessException;

    public List<Article> selectMany() throws DataAccessException;

    public int updateOne(Article article) throws DataAccessException;

    public int deleteOne(int id) throws DataAccessException;
}
