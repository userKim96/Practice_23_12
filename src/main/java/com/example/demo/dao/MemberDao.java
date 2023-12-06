package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberDao {
	


	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM `member`
				WHERE id = #{id}
			""")
	public Member getMemberById(int id);
	
	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW(regDate)
					, updateDate = NOW(updateDate)
					, loginId = #{loginId}
					, loginPw = #{loginPw}
					, `name` = #{name}
					, nickname = #{nickname}
					, cellphoneNum = #{cellphoneNum}
					, email = #{email}
			""")
	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email);

	@Select("""
			SELECT COUNT(*)
				FROM `member`
				WHERE loginId = #{loginId}
			""")
	public int isLoginIdByloginId(String loginId);

	@Select("""
			SELECT *
				FROM `member`
				WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);



}
