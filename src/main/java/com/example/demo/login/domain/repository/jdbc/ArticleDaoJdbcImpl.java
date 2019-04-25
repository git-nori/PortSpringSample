package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.repository.ArticleDao;

public class ArticleDaoJdbcImpl implements ArticleDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int count() throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM article";
        int count = jdbc.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public int insertOne(Article article) throws DataAccessException {
        String sql = "INSERT INTO article("
                + " user_id,"
                + " title,"
                + " content)"
                + " VALUES(?, ?, ?)";
        int rowNumber = jdbc.update(sql
                , article.getUserId()
                , article.getTitle()
                , article.getContent());

        return rowNumber;
    }

    @Override
    public Article selectOne(int id) throws DataAccessException {
        String sql = "SELECT * FROM article WHERE id = ?";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        return jdbc.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Article> selectMany() throws DataAccessException {
        String sql = "SELECT * FROM article";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        return jdbc.query(sql, rowMapper);
    }

    @Override
    public int updateOne(Article article) throws DataAccessException {
        String sql = "UPDATE article SET"
                + "title = ?,"
                + " content = ?"
                + " WHERE id = ?";

        int rowNumber = jdbc.update(sql, article.getId());
        return rowNumber;
    }

    @Override
    public int deleteOne(int id) throws DataAccessException {
        String sql = "DELETE FROM article WHERE id = ?";
        int rowNumber = jdbc.update(sql, id);
        return rowNumber;
    }

}
