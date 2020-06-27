package com.semi.member.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.member.exception.MemberException;
import com.semi.member.model.dao.MemberDAO;
import com.semi.member.model.vo.Member;

public class MemberService {
	private Connection con;
	private MemberDAO mDAO = new MemberDAO();
	
	// 정보 추가
	public int insertMember(Member m) throws MemberException {
		con = getConnection();
		int result = mDAO.insertMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Member selectMember(Member m) throws MemberException {
		con = getConnection();
		Member result = mDAO.selectMember(con, m);

		close(con);
		
		if(result == null) throw new MemberException("[Service에러] : 로그인 실패!");
		
		return result;
	}

	public int updateMember(Member m) throws MemberException{
		con = getConnection();
		
		int result = mDAO.updateMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
		
	}
	
	// 회원 탈퇴
	public int deleteMember(String userid, String userpwd) throws MemberException{
		// TODO Auto-generated method stub
		con = getConnection();
		
		int result = mDAO.deleteMember(con, userid, userpwd);
		
		if(result > 0) {
			commit(con);
		} else { // Login일때는, MemberException,
			rollback(con);
		}
		
		close(con);
		
		return result;
		
	}
	
	public int idDupCheck(String id) {
		con = getConnection();
		
		int result = mDAO.idDupCheck(con, id);
		
		close(con);
		
		return result;
	}
}