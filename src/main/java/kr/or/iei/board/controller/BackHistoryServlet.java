package kr.or.iei.board.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BackHistoryServlet
 */
@WebServlet("/board/backHistory")
public class BackHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BackHistoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Start from BackHistoryServlet");

		HttpSession session = request.getSession(false);
		String pageId = request.getParameter("pageId");

		int result = 0;

		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<String> viewedPages = (Set<String>) session.getAttribute("pageId");
			if (viewedPages != null && pageId != null) {
				viewedPages.remove(pageId);
				result = 1;
			}
		}

		System.out.println("End from BackHistoryServlet");

		response.getWriter().print(result);
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
