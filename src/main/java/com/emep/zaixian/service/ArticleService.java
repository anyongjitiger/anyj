package com.emep.zaixian.service;

import java.util.List;

import com.emep.zaixian.model.Article;

public interface ArticleService {
	
	public Article createArticle(Article article);
	
	List<Article> findAll();
	
	public Article findArticleByTitle(String title);
	
	public Article findById(Long id);
	
	public Long getNextById(Long id);
	
	public Long getPrevById(Long id);
	
	public boolean isArticleExist(Article article);
	
	public void deleteArticle(Long id);
}
