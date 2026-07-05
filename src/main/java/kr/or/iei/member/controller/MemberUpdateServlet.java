package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberUpdateServlet() {
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
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberEmail = request.getParameter("memberEmail");
		String memberPhone = request.getParameter("memberPhone");
		String memberAddr = request.getParameter("memberAddr");

		Member member = new Member();
		member.setMemberNo(memberNo);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddr(memberAddr);

		MemberService service = new MemberService();
		int result = service.updateMember(member);

		if (result > 0) {
			// session 은 사용자가 처음 접속했으때 생기는 객체
			// true : 기존 새션 반환, 없으면 새로 만들어서
			// false 기존 새션 반환, 없으면 null 반환
			HttpSession session = request.getSession(false);
			// Object 로 반환되기 때문에 Member 로 Down casting
			Member loginMember = (Member) session.getAttribute("loginMember");

			loginMember.setMemberPw(memberPw);
			loginMember.setMemberName(memberName);
			loginMember.setMemberEmail(memberEmail);
			loginMember.setMemberPhone(memberPhone);
			loginMember.setMemberAddr(memberAddr);

			request.setAttribute("title", "알림");
			request.setAttribute("text", "회원정보가 수정되었습니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/myPage");
		} else {
			request.setAttribute("title", "알림");
			request.setAttribute("text", "회원정보 수정중 오류가 발생하였습니다");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/member/myPage");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

		// 4. 결과 처리
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
