package com.semi.goal.model.dao;

import static com.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.semi.goal.model.vo.Goal;
import com.semi.member.model.vo.Member;

public class GoalDAO {
	private Properties prop;
	
	public GoalDAO() {
		prop = new Properties();
		
		String filePath = GoalDAO.class
				.getResource("/config/goal.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertGoal(Connection con, Goal g) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertGoal"); // goal.properties 에서 작성후 가져올것
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, g.getGcategory());
			pstmt.setString(2, g.getGoalname());
			pstmt.setString(3, g.getGcontent());
			pstmt.setString(4, g.getGoalinone());
			pstmt.setInt(5, g.getGmaxnum());
			pstmt.setDate(6, g.getStartdate());
			pstmt.setDate(7, g.getEnddate());
			pstmt.setString(8, g.getGimg());
			pstmt.setString(9, g.getGwriter());
			
			
			result= pstmt.executeUpdate();// DB 실행 안함
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int getListCount(Connection con) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listCount"); // goal.properties 에서 가져옴
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Goal> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Goal> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllGoal");  //goal.properties 에서 가져옴
		
		try {
			pstmt = con.prepareStatement(sql);
			
			// 1. 마지막 행의 번호를 가져옴
			// 2. 첫 행의 번호를 가져옴
			int startRow = (currentPage -1) * limit +1; // 첫번째 글 가져옴
			int endRow = startRow + limit -1;
			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Goal>();
			
			while(rset.next()) {
				Goal g = new Goal();
				
				g.setGno(rset.getInt("GNO"));
				g.setGcategory(rset.getString("GCATEGORY"));
				g.setGoalname(rset.getString("GOALNAME"));
				g.setGcontent(rset.getString("GCONTENT"));
				g.setGoalinone(rset.getString("GOALINONE"));
				g.setGmaxnum(rset.getInt("GMAXNUM"));
				g.setGcurrnum(rset.getInt("GCURRNUM"));
				g.setGspoint(rset.getInt("GSPOINT"));
				g.setEnrolldate(rset.getDate("ENROLL_DATE"));
				g.setStartdate(rset.getDate("START_DATE"));
				g.setEnddate(rset.getDate("END_DATE"));
				g.setPercent(rset.getInt("PERCENT"));
				g.setGstatus(rset.getString("GSTATUS"));
				g.setGimg(rset.getString("GIMG"));
				g.setGwriter(rset.getString("GWRITER"));
		
				
				list.add(g);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println(list);
		
		return list;
	}

	public int goalJoin(Connection con, Goal g) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("goalJoin"); // goal.properties 에서 작성후 가져올것
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, g.getGwriter());
			pstmt.setInt(2, g.getGno());
			pstmt.setInt(3, g.getGspoint());
			
			result = pstmt.executeUpdate();// DB 실행 안함
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	
	}

	public HashMap<String, Object> selectOneList(Connection con, int gno, String gWriter) {
		Goal g = null;
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap map = null;

		String sql = prop.getProperty("selectOneGoal");

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gWriter);
			pstmt.setInt(2, gno);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				g = new Goal();
				m = new Member();
				
				g.setGno(rset.getInt("gno"));
				g.setGcategory(rset.getString("gcategory"));
				g.setGoalname(rset.getString("goalname"));
				g.setGcontent(rset.getString("gcontent"));
				g.setGoalinone(rset.getString("goalinone"));
				g.setGmaxnum(rset.getInt("gmaxnum"));
				g.setGcurrnum(rset.getInt("gcurrnum"));
				g.setGspoint(rset.getInt("gspoint"));
				g.setEnrolldate(rset.getDate("enroll_date"));
				g.setStartdate(rset.getDate("start_date"));
				g.setEnddate(rset.getDate("end_date"));
				g.setPercent(rset.getInt("percent"));
				g.setGstatus(rset.getString("gstatus"));
				g.setGimg(rset.getString("gimg"));
				g.setGwriter(rset.getString("gwriter"));
				
				m.setMimage(rset.getString("m_Image"));
				
				map = new HashMap<String, Object>();
				
				map.put("goal", g);
				map.put("member", m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return map;
	}
	
	

}
