package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultDate;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultDate doWrite(String title, String body) {
		
		if (Util.empty(title) ) {
			return ResultDate.from("F-1", "제목을 입력해주세요.");
		}
		
		if (Util.empty(body) ) {
			return ResultDate.from("F-2", "내용을 입력해주세요.");
		}
		
		articleService.writeArticle(title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultDate.from("S-1",Util.f("%d 게시물이 작성되었습니다.", id), articleService.getArticleById(id));
		
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultDate doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultDate.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return ResultDate.from("S-1", Util.f("%d번 게시물을 삭제했습니다.", id));
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultDate doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultDate.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.modifyArticle(id, title, body);
		
		return ResultDate.from("S-1", Util.f("%d번 게시물을 수정했습니다.", id));
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public ResultDate showList() {
		
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			return ResultDate.from("F-1", "게시물이 존재하지 않습니다.");
		}
		
		return ResultDate.from("S-1", "게시물 목록", articles);
	}
	
	@RequestMapping("/usr/article/showDetail")
	@ResponseBody
	public ResultDate showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultDate.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		return ResultDate.from("S-1", Util.f("%d번 게시물", id), article);
	}
	
}