package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/member/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩 - 필터
		// 2. 값 추출 - x
		// 3. 비즈니스 로직 - x

		// - 요청 JSP 에서 전달한 파라미터에 의한 로직이 존재하는가?
		// - 응답 JSP 에서 화면에 출력 등 필요한 데이터가 존재하는가?
		// - 마이페이지에서 회원에 대한 정보 출력
		// - 해당데이터가 DB에 종속된 데이터인가?

		// 4. 결과처리 - 마이페이지로 이동(회원에 대한 정보 출력)
		// 세션에 등록된 데이터
		request.getRequestDispatcher("/WEB-INF/views/member/myPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
