package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrMemberController {

	private MemberService memberService;
	private Rq rq;

	UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultDate<Member> doJoin(String loginId, String loginPw, String name, String nickname,
			String cellphoneNum, String email) {
		
		if (rq.getLoginedMemberId() != 0) {
			return ResultDate.from("F-L", "이미 로그인한 상태입니다.");
		}

		if (Util.empty(loginId)) {
			return ResultDate.from("F-1", "아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return ResultDate.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Util.empty(name)) {
			return ResultDate.from("F-3", "이름을 입력해주세요");
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

		return ResultDate.from("S-1", "회원 가입 성공", memberService.getMemberById(id));
	}

	@RequestMapping("/usr/member/login")
	public String Login() {

		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
		if (rq.getLoginedMemberId() != 0) {
			return Util.jsHistoryBack("이미 로그인한 상태입니다.");
		}
		
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.jsHistoryBack("존재하지 않는 아이디입니다.");
		}
		
		if (member.getLoginPw().equals(Util.sha256(loginPw)) == false) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요");
		}
		
		rq.login(member);
		
		return Util.jsReplace(Util.f("%s님 로그인 되었습니다.", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		rq.logout();
		
		return Util.jsReplace("로그아웃 되었습니다.", "/");
	}

}