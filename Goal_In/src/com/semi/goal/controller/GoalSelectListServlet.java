package com.semi.goal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.goal.model.service.GoalService;
import com.semi.goal.model.vo.Goal;
import com.semi.goal.model.vo.PageInfo;

/**
 * Servlet implementation class GoalSelectListServlet
 */
@WebServlet("/goalList.go")
public class GoalSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoalSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 게시판 목록 처리 서블릿이다.
		ArrayList<Goal> list = new ArrayList<Goal>();
		GoalService gs = new GoalService();
		
//		Goal g = new Goal();                                           // 추가
//		
//		if (g.getGcategory().equals("식단")) {                  //추가
//			
//			
//		} else if (g.getGcategory().equals("유산소")) {     //추가
//			
//			
//		} else if (g.getGcategory().equals("웨이트")) {     //추가
//			
//			
//		} else if (g.getGcategory().equals("스트레칭")) {     //추가
//			
//			
//		} else if (g.getGcategory().equals("생활습관")) {    //추가
//			
//			
//		} else {                                                               //추가
//			
//		}
		
		// 페이징(페이지로 쪼개는 작업) 처리에 필요한 변수들
		// 1, 2, 3, . . . 5 - > 6, 7, 8, 9, 10
		int startPage; //시작 페이지
		
		// 한 번에 처리할 페이지들 중 가장 마지막 페이지
		// 1, 2, 3 . . . 5/ 6, 7, 8, 9, 10/ 11, 12
		int endPage; // 5, 10, 12 인 마지막 페이지
		
		// 전체 페이지들 중 가장 끝 페이지
		int maxPage; // 12페이지
		
		// 현재 사용자가 보고 있                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       3 는 페이지
		int currentPage;
		
		// 한 페이지 당 보여줄 게시글 수 
		int limit;
		
		// 접속한 사용자가 처음으로 볼 페이지 초기화 하기
		currentPage = 1;
		
		// 최대 게시글 10개씩
		limit = 9;
		
		//만약에 사용자가 현재 페이지 정보를 가지고 있다면 
		// currentPage 정보 변경하기
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 총 게시글 수 가져오기 
		int listCount = gs.getListCount();
		
		System.out.println("총 게시글 수 : " + listCount);
		
		// 총 게시글 300개!
		// 30개 페이지
		// 만약에 전체 게시글 수가 13개라면 페이지는 2개 나와야 한다.
		// ** 짜투리 게시글도 하나의 페이지를 잡아 먹는다.
		// 13 --> 1.3 --(올림)--> 2로
		maxPage = (int)((double)listCount/limit + 0.9); // 게시글 수를  
		
		// 한번에 보일 시작 페이지와 끝 페이지를 계산하기
		// 시작 페이지
		// 1 ~ 10 : 1
		// 11 ~ 20 : 11
		startPage = ((int)((double)currentPage / limit+0.9) - 1) * limit +1;
		
		// 끝 페이지
		// 1 ~ 10 : 10
		// 11 ~ 20 : 20
		endPage = startPage + limit - 1;
		
		// 만약 마지막 페이지가 전체 기준 페이지보다 크다면
		// 즉, 총 13개 페이지밖에 안나오는데 20까지 잡았다면 안되겠죠?
		if( endPage > maxPage) {
			endPage = maxPage;
		}
		
		// ---------------------- 페이징 처리는 끝
		
		list = gs.selectList(currentPage, limit);
		String page = "";
		if(list != null) {
			
			PageInfo pi = new PageInfo(currentPage, listCount, limit, 
					                                    maxPage, startPage, endPage);
			// request.setAttribute("currentPage", currentPage);
			// request.setAttribute("listCount", listCount);
			// . . . . . . 계속 써줘야 하는데 객체를 만들어서 관리 객체는 PageInfo.java로 만듬
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			page = "views/goal/goalAllList.jsp";
		} else {
			request.setAttribute("error-msg", "게시글 목록 조회 실패");
			page = "views/common/errorPage.jsp";
		}
		
			request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
