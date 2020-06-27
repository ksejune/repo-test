package com.semi.goal.controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.goal.model.service.GoalService;
import com.semi.goal.model.vo.Goal;

/**
 * Servlet implementation class GoalSelectOneServlet
 */
@WebServlet("/gsol.go")
public class GoalSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoalSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));
		String gWriter = request.getParameter("gWriter");
		
	      System.out.println(gno);
	      System.out.println(gWriter);
	      HashMap<String, Object> map = new GoalService().selectOne(gno,gWriter);
	      
			
	      String page = "";
	      
	      if(map != null) {
	         request.setAttribute("goal", map.get(1));
	         request.setAttribute("member", map.get(2));
	         page = "views/goal/goalDetail.jsp";
	      } else {
	         request.setAttribute("error-msg", "게시글 상세 보기 실패");
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
