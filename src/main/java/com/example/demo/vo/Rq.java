package com.example.demo.vo;

import java.io.IOException;

import com.example.demo.Util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
	HttpServletResponse resp;
	HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse response) {
		
		this.resp = response;
		
		this.session = req.getSession();
		
		int logindMemberId = 0;
		
		if (session.getAttribute("logindMemberId") != null) {
			logindMemberId = (int) session.getAttribute("logindMemberId");
		}
		
		this.loginedMemberId = logindMemberId;
	}

	public void jsPrintHistoryBack(String msg) {
		
		resp.setContentType("text/html; charset=UTF-8;");
		
		try {
			resp.getWriter().append(Util.jsHistoryBack(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
	}
}
