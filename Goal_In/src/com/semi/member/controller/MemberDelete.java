package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.semi.common.PwdEncryptor;
import com.semi.member.exception.MemberException;
import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/mDelete.me")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 2. multipart/form-data 형식으로 전송되었는지 확인하기
		
//		if ( ! ServletFileUpload.isMultipartContent(request)) { // 멀티 파트로 안보냈다면
//			request.setAttribute("error-msg", "multipart로 전송되지 않았습니다.");
//
//			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
//		}
		
		
		// 회원 아이디 가져오기
		HttpSession session = request.getSession(false);
				
		String userid = ((Member)session.getAttribute("member")).getUserid();
		String userpwd = PwdEncryptor.encodePwd(request.getParameter("userpwd")); // admin

		 System.out.println("회원 기존 아이디  : " + userid); 
		 System.out.println("회원 입력 비밀번호  : " + request.getParameter("userpwd"));

				
				
				MemberService ms = new MemberService();
				
				try {
//					int result = ms.deleteMember(userid, userpwd); // 0, 1
					int result = 1;
					
//					System.out.println("회원 탈퇴 성공!");
					if (result > 0) {
//						session.invalidate();// 탈퇴했으면 로그인도 취소
//						response.sendRedirect("index_.jsp");
					} else {

					}

					new Gson().toJson(result, response.getWriter());

				} catch(Exception e) {
					request.setAttribute("error-msg", "회원 탈퇴 수행 중 에러 발생!");
					request.setAttribute("exception", e);
					
					request
					.getRequestDispatcher("views/common/errorPage.jsp")
					.forward(request, response);
				}
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
