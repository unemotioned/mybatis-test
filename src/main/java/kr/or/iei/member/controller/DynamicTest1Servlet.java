package kr.or.iei.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class DynamicTest1Servlet
 */
@WebServlet("/member/dynamic/test1")
public class DynamicTest1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DynamicTest1Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sFlag1 = request.getParameter("sFlag1");
		String sFlag2 = request.getParameter("sFlag2");
		String sFlag3 = request.getParameter("sFlag3");
		String sFlag4 = request.getParameter("sFlag4");

		Member member = new Member();
		member.setSFlag1(sFlag1);
		member.setSFlag2(sFlag2);
		member.setSFlag3(sFlag3);
		member.setSFlag4(sFlag4);

		MemberService service = new MemberService();
		ArrayList<Member> list = service.selDynamicTest1(member);

		request.setAttribute("memberList", list);
		request.getRequestDispatcher("/views/dynamicTest1.jsp").forward(request, response);
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
