package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.service.MemberService;
import com.example.demo.vo.ResultDate;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/article/doJoin")
	@ResponseBody
	public ResultDate doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
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
		
		int isLoginId = memberService.isLoginId(loginId);
		
		if (isLoginId != 0) {
			return ResultDate.from("F-7", Util.f("이미 사용중인 아이디(%s) 입력입니다.", loginId));
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		return ResultDate.from("S-1","회원 가입 성공", memberService.getMemberById(id));
	}
	
}