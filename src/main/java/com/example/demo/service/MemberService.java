package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		memberDao.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

	public int getLastInsertId() {
		return memberDao.getLastInsertId();
				
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public boolean isLoginIdByloginId(String loginId) {
		if ( memberDao.isLoginIdByloginId(loginId) == 0) {
			return false;
		}
		return true;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
