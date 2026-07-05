package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/member/delete")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteMemberServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberNo = request.getParameter("memberNo");

		MemberService service = new MemberService();
		int result = service.deleteMember(memberNo);

		if (result > 0) {
			HttpSession session = request.getSession(false);

			if (session != null) {
				session.invalidate();
			}

			request.setAttribute("title", "알림");
			request.setAttribute("text", "회원정보가 삭제 되었습니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/");
		} else {
			request.setAttribute("title", "알림");
			request.setAttribute("text", "회원정보 삭제 중 오류가 발하였습니다");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/member/myPage");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
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
