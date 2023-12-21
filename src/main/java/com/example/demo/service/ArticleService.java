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

	public ResultDate<Integer> increaseHitCount(int id) {
		
		int affectedRowsCnt = articleDao.increaseHitCount(id);
		
		if (affectedRowsCnt == 0) {
			return ResultDate.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id), affectedRowsCnt);
		}
		
		return ResultDate.from("S-1", "조회수 증가", affectedRowsCnt);
	}


	public int getArticleHitCount(int id) {
		return articleDao.getArticleHitCount(id);
	}

}
