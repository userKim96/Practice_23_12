package com.example.demo.vo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	
	@Getter
	private int logindMemberId;
	
	public Rq(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		int logindMemberId = 0;
		
		if (session.getAttribute("logindMemberId") != null) {
			logindMemberId = (int) session.getAttribute("logindMemberId");
		}
		
		this.logindMemberId = logindMemberId;
	}
		
}
