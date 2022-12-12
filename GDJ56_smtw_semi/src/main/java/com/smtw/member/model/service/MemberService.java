package com.smtw.member.model.service;

import static com.smtw.common.JDBCTemplate.*;

import java.sql.Connection;

import com.smtw.member.model.dao.MemberDao;
import com.smtw.member.model.vo.Member;

public class MemberService {
	private MemberDao dao=new MemberDao();
	
	//회원가입
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//아이디,비번으로 회원검색(로그인)
	public Member searchIdPwd(String logInId, String logInPwd) {
		Connection conn=getConnection();
		Member m=dao.searchIdPwd(conn,logInId,logInPwd);
		close(conn);
		return m;
	}
}