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
	HttpServletRequest req;
	HttpServletResponse resp;
	HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse response) {
		
		this.resp = response;
		this.req = req;
		
		this.session = req.getSession();
		
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
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

	public String jsReturnOnView(String msg) {
		
		req.setAttribute("msg", msg);
		
		return "usr/common/js";
	}
}
