package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/article/doJoin")
	@ResponseBody
	public ResultDate<Member> doJoin(HttpSession session, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (session.getAttribute("logindMemberId") != null) {
			return ResultDate.from("F-L", "이미 로그인한 상태입니다.");
		}
		
		if (Util.empty(loginId)) {
			return ResultDate.from("F-1", "아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return ResultDate.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return  ResultDate.from("F-3", "이름을 입력해주세요");
		}
		if (Util.empty(nickname)) {
			return ResultDate.from("F-4", "닉네임을 입력해주세요");
		}
		if (Util.empty(cellphoneNum)) {
			return ResultDate.from("F-5", "번호를 입력해주세요");
		}
		if (Util.empty(email)) {
			return ResultDate.from("F-6", "이메일을 입력해주세요");
		}
		
		boolean isLoginId = memberService.isLoginIdByloginId(loginId);
		
		if (isLoginId == true) {
			return ResultDate.from("F-7", Util.f("이미 사용중인 아이디(%s) 입력입니다.", loginId));
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		return ResultDate.from("S-1","회원 가입 성공", memberService.getMemberById(id));
	}

		@RequestMapping("/usr/article/doLogin")
		@ResponseBody
		public ResultDate doLogin(HttpSession session, String loginId, String loginPw) {
			
			if (session.getAttribute("logindMemberId") != null) {
				return ResultDate.from("F-L", "이미 로그인한 상태입니다.");
			}
			
			if (Util.empty(loginId)) {
				return ResultDate.from("F-1", "아이디를 입력해주세요");
			}
			if (Util.empty(loginPw)) {
				return ResultDate.from("F-2", "비밀번호를 입력해주세요");
			}
			
			Member member = memberService.getMemberByLoginId(loginId);
			
			if (member == null) {
				return ResultDate.from("F-3", Util.f("%s은(는) 존재하지 않는 입력입니다.", loginId));
			}
			
			if (member.getLoginPw().equals(loginPw) == false) {
				return ResultDate.from("F-4","비밀번호가 일치하지 않습니다.");
			}
			
			session.setAttribute("logindMemberId", member.getId());
			
			return ResultDate.from("S-1", Util.f("%s님 로그인 되었습니다.", member.getNickname()));
	}
		@RequestMapping("/usr/article/doLogout")
		@ResponseBody
		public ResultDate doLogout(HttpSession session) {

			if (session.getAttribute("logindMemberId") == null) {
				return ResultDate.from("F-L", "이미 로그아웃한 상태입니다.");
			}
			
			session.removeAttribute("logindMember");
			
			return ResultDate.from("S-1", "로그아웃 되었습니다.");
	}
	
}