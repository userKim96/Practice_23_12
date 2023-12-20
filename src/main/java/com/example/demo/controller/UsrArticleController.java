package com.example.demo.controller;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;
	
	UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

	@RequestMapping("/usr/article/write")
	public String write(Model model) {
		
		int writerAuthLevel = articleService.getWriterAuthLevel(rq.getLoginedMemberId());
		
		model.addAttribute("writerAuthLevel", writerAuthLevel);
		
		return "usr/article/write";
		
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(Model model, String title, String body, int boardId) {
		
		if (Util.empty(title) ) {
			return Util.jsHistoryBack("제목을 입력해주세요.");
		}
		
		if (Util.empty(body) ) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);
		
		
		int id = articleService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d 게시물이 작성되었습니다.", id), Util.f("detail?id=%d", id));
		
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
	
		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if (rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 권한이 없습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제했습니다.", id), "list");
	}
	
	@RequestMapping("/usr/article/modify")
	public String Modify(Model model, int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if (rq.getLoginedMemberId() != (article.getMemberId())) {
			return rq.jsReturnOnView(Util.f("%d번 게시물에 권한이 없습니다.", id));
		}
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if (rq.getLoginedMemberId() != (article.getMemberId())) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 권한이 없습니다.", id));
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다.", id), Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "keywordTitle") String searchKeywordType) {
		
		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다.");
		}
		
		Board board = boardService.getBoardById(boardId);
		
		if (board == null) {
			return rq.jsReturnOnView("존재하지 않는 게시판입니다.");
		}
		
		int articlesCnt = articleService.getArticlesCnt(boardId, searchKeyword, searchKeywordType);
		
		int itemsInAPage = 10;
		
		int limitStart = (page - 1) * itemsInAPage;
		
		int pagesCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage);
		
		List<Article> articles = articleService.getArticles(boardId, limitStart, itemsInAPage, searchKeyword, searchKeywordType);
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("searchKeywordType", searchKeywordType);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String detail(Model model, int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		model.addAttribute("article", article);
		model.addAttribute("logindMemberId", rq.getLoginedMemberId());
		return "usr/article/detail";
	}
	
}