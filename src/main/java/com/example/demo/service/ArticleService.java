package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Util.Util;
import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultDate;

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
	
	public List<Article> getArticles(int boardId, int limitStart, int itemsInAPage, String searchKeyword, String searchKeywordType) {
		
		return articleDao.getArticles(boardId, limitStart, itemsInAPage, searchKeyword, searchKeywordType);
	}


	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}


	public int getArticlesCnt(int boardId, String searchKeyword, String searchKeywordType) {
		return articleDao.getArticlesCnt(boardId, searchKeyword, searchKeywordType);
	}


	public int getWriterAuthLevel(int loginedMemberId) {
		return articleDao.getWriterAuthLevel(loginedMemberId);
	}

	public void increaseHitCount(int id) {
		articleDao.increaseHitCount(id);
	}


	public int getArticleHitCount(int id) {
		return articleDao.getArticleHitCount(id);
	}

}
