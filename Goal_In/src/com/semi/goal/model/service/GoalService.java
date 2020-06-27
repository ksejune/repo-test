package com.semi.goal.model.service;


import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.semi.goal.model.dao.GoalDAO;
import com.semi.goal.model.vo.Goal;

public class GoalService {
	private Connection con;
	private GoalDAO gDAO = new GoalDAO();
	
	public int insertGoal(Goal g) {
		con = getConnection();
		
		int result1 = gDAO.insertGoal(con, g);
		int result3=0; // 둘다 성공 했을 때 리턴 값
		
			if(result1 >0) { // goalinsert 성공
				commit(con);    // 추가저장
				System.out.println("골은 등록 했다?");
				
				
				int result2= gDAO.goalJoin(con, g);  // 추가
				
					if(result2 > 0) {
						result3=1;
						commit(con);
					} else {
						System.out.println("Joingoal 실패");
					}
					
			}else {
				System.out.println("insertGoal 실패");
			}
			
			if(result3==0) rollback(con);
			
		close(con);
		
		return result3;                    
	}
	
	public int getListCount() {
		con = getConnection();
		
		int result = gDAO.getListCount(con);
		
		close(con);
		
		return result;
	}
	
	public ArrayList<Goal> selectList(int currentPage, int limit) {
		con = getConnection();
		
		ArrayList<Goal> list = gDAO.selectList(con, currentPage, limit);
		
		close(con);
		
		return list;
	}

	public HashMap<String, Object> selectOne(int gno, String gWriter) {
			con = getConnection();
			HashMap<String, Object> hmap = gDAO.selectOneList(con, gno, gWriter);
			
			close(con);
			
			return hmap;
		}
	
	
}
	
	
