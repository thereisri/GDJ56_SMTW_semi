package com.smtw.member.model.dao;

import static com.smtw.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.smtw.member.model.vo.Member;

public class MemberDao {

		private Properties sql=new Properties();
		
		public MemberDao() {
			try {
				String path=MemberDao.class
						.getResource("/sql/member/member_sql.properties")
						.getPath();
				sql.load(new FileReader(path));
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
		
	//회원가입
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertMember"));
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getBirth());
			pstmt.setString(7, String.valueOf(m.getGender()));
			pstmt.setString(8, m.getAddress());
			
			result=pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//아이디,비밀번호로 회원검색(로그인)
	public Member searchIdPwd(Connection conn, String logInId, String logInPwd) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchIdPwd"));
			pstmt.setString(1, logInId);
			pstmt.setString(2, logInPwd);
			rs=pstmt.executeQuery();
			
			if(rs.next()) m=getMember(rs);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
		
	
	private Member getMember(ResultSet rs) throws SQLException {
		return Member.builder()
				.memberId(rs.getString("member_id"))
				.memberPwd(rs.getString("member_pwd"))
				.memberName(rs.getString("member_name"))
				.email(rs.getString("email"))
				.phone(rs.getString("phone"))
				.birth(rs.getString("birth"))
				.gender(rs.getString("gender").charAt(0))
				.address(rs.getString("address"))
				.myImg(rs.getString("myimg"))
				.emailAgree(rs.getString("email_agree").charAt(0))
				.build();
	}
}