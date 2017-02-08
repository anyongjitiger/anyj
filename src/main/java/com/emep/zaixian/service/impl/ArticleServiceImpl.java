package com.emep.zaixian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emep.zaixian.dao.ArticleDao;
import com.emep.zaixian.model.Article;
import com.emep.zaixian.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleDao articleDao;

	@Override
	public Article createArticle(Article article){
		return articleDao.createArticle(article);
	}
	
	@Override
    public List<Article> findAll(){
		return articleDao.getArticles();
	}
	
	@Override
	public Article findArticleByTitle(String title){
		return articleDao.getArticleByTitle(title);
	}
	
	@Override
	public Article findById(Long id){
		return articleDao.getArticleById(id);
	}
	
	@Override
	public Long getNextById(Long id){
		return articleDao.getNextById(id);
	};
	
	@Override
	public Long getPrevById(Long id){
		return articleDao.getPrevById(id);
	};
	
	@Override
	public boolean isArticleExist(Article article){
		return findById(article.getId())!=null;
	}
	
	@Override
	public void deleteArticle(Long id){
		articleDao.deleteArticle(id);
	}
}
