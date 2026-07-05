package kr.or.iei.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.board.model.service.BoardService;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/board/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ?delNo=1&delNo=2&delNo=3... 이런 식으로 전달됨
		String[] delNo = request.getParameterValues("delNo");

		BoardService service = new BoardService();
		int result = service.deleteBoard(delNo);

		if (result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("text", "게시글이 삭제되었습니다");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("text", "게시글 삭제 도중 오류가 발생");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/board/getList?reqPage=1");
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
