package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	
	private ArticleDao articleDao;
	
	ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	public Article forPrintArticle(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}
	
	public void writeArticle(int memberId, String title, String body ) {
		articleDao.writeArticle(title, body, memberId);
	}
	
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}


	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

}
