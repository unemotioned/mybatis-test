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
 * Servlet implementation class DynamicIfTestServlet
 */
@WebServlet("/member/dynamic/ifTest")
public class DynamicIfTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DynamicIfTestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String chkNo = request.getParameter("chkNo");
		String chkId = request.getParameter("chkId");
		String chkName = request.getParameter("chkName");
		String chkEmail = request.getParameter("chkEmail");
		String chkPhone = request.getParameter("chkPhone");

		Member member = new Member();
		member.setSFlag1(chkNo);
		member.setSFlag2(chkId);
		member.setSFlag3(chkName);
		member.setSFlag4(chkEmail);
		member.setSFlag5(chkPhone);

		MemberService service = new MemberService();
		ArrayList<Member> list = service.selDynamicIfTest(member);

		request.setAttribute("list", list);
		request.setAttribute("chkInfo", member);
		request.getRequestDispatcher("/views/dynamicIfTest.jsp").forward(request, response);
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
