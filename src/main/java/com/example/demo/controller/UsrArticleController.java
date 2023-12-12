package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultDate<Article> doWrite(HttpServletRequest req, String title, String body) {
		
		Rq rq = new Rq(req);
		
		if (rq.getLogindMemberId() == 0) {
			return ResultDate.from("F-L", "로그인 후 이용가능합니다.");
		}
		
		if (Util.empty(title) ) {
			return ResultDate.from("F-1", "제목을 입력해주세요.");
		}
		
		if (Util.empty(body) ) {
			return ResultDate.from("F-2", "내용을 입력해주세요.");
		}
		
		articleService.writeArticle(rq.getLogindMemberId(), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultDate.from("S-1",Util.f("%d 게시물이 작성되었습니다.", id), articleService.getArticleById(id));
		
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		
		Rq rq = new Rq(req);
		
		if (rq.getLogindMemberId() == 0) {
			return Util.jsHistoryBack("로그인 후 이용가능합니다.");
		}
		
		Article article = articleService.getArticleById(id);
	
		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if (rq.getLogindMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 권한이 없습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제했습니다.", id), "list");
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultDate doModify(HttpSession session, int id, String title, String body) {
		
		if (session.getAttribute("logindMemberId") == null) {
			return ResultDate.from("F-L", "로그인 후 이용가능합니다.");
		}
		
		Article article = articleService.getArticleById(id);
			
		if (article == null) {
			return ResultDate.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if (session.getAttribute("logindMemberId").equals(article.getMemberId()) == false) {
			return ResultDate.from("F-1", Util.f("%d번 게시물에 권한이 없습니다.", id));
		}
		
		articleService.modifyArticle(id, title, body);
		
		return ResultDate.from("S-1", Util.f("%d번 게시물을 수정했습니다.", id));
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		
		Rq rq = new Rq(req);
		
		Article article = articleService.forPrintArticle(id);
		
		model.addAttribute("article", article);
		model.addAttribute("logindMemberId", rq.getLogindMemberId());
		return "usr/article/detail";
	}
	
}