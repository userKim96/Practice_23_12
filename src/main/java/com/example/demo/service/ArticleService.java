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
	
	public void writeArticle(int memberId, String title, String body, int boardId) {
		articleDao.writeArticle(title, body, memberId, boardId);
	}
	
	public List<Article> getArticles(int boardId) {
		return articleDao.getArticles(boardId);
	}


	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}


	public int getArticlesCut(int boardId) {
		return articleDao.getArticlesCut(boardId);
	}


	public int getWriterAuthLevel(int loginedMemberId) {
		return articleDao.getWriterAuthLevel(loginedMemberId);
	}

}
