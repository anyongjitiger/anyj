package com.emep.zaixian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.emep.zaixian.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public Article createArticle(final Article article){
		final String sql = "insert into article (title, url, content,date) values(?, ?, ?, ?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, article.getTitle());
                psst.setString(count++, article.getUrl());
                psst.setString(count++, article.getContent());
                Date date = new Date();
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			String today = sdf.format(date);
                psst.setString(count++, today);
                return psst;
            }
        }, keyHolder);
        article.setId(keyHolder.getKey().longValue());
        return article;
	};
	
	@Override
    public List<Article> getArticles() {
        final String sql = "select * from article order by id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Article.class));
    }
	@Override
	public Article getArticleByTitle(String title) {
		String sql = "select * from article where title = ?";
        List<Article> articleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Article.class), title);
        if(articleList.size() == 0) {
            return null;
        }
        return articleList.get(0);
	}
	@Override
	public Article getArticleById(Long id) {
		String sql = "select * from article where id = ?";
        List<Article> articleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Article.class), id);
        if(articleList.size() == 0) {
            return null;
        }
        return articleList.get(0);
	}
	@Override
	public Long getNextById(Long id){
		String sql = "select * from article where id = (select max(id) from article where id < ?)";
		List<Article> articleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Article.class), id);
		if(articleList.size() == 0) {
            return null;
        }
		return articleList.get(0).getId();
	}
	@Override
	public Long getPrevById(Long id){
		String sql = "select * from article where id = (select min(id) from article where id > ?)";
		List<Article> articleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Article.class), id);
		if(articleList.size() == 0) {
            return null;
        }
		return articleList.get(0).getId();
	}
	@Override
	public void deleteArticle(Long id){
		String sql = "delete from article where id=?";
        jdbcTemplate.update(sql, id);
	}
	
	@Override
	public Article updateArticle(Article article){
		final String sql = "update article set title=?, url=?, content=?, date=? where id=?";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		article.setDate(today);
        jdbcTemplate.update(
                sql,
                article.getTitle(), article.getUrl(), article.getContent(), today, article.getId());
        return article;
	}
}
