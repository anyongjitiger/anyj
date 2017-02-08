package com.emep.zaixian.dao;

import java.util.List;

import com.emep.zaixian.model.Article;

public interface ArticleDao {
	public Article createArticle(Article article);
	public Article getArticleByTitle(String title);
	public Article getArticleById(Long id);
	public Long getNextById(Long id);
	public Long getPrevById(Long id);
	public List<Article> getArticles();
	public void deleteArticle(Long id);
}
