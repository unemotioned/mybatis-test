package kr.or.iei.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.BoardPageData;

/**
 * Servlet implementation class GetListServlet
 */
@WebServlet("/board/getList")
public class GetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int reqPage = request.getParameter("reqPage") != null ? Integer.parseInt(request.getParameter("reqPage")) : 1;

		// 게시글 목록 하단에서 검색하였을 떄 전달한 파라미터
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("srchKeyword");

		BoardService service = new BoardService();
		BoardPageData pd = service.selectAllBoardList(reqPage, searchType, searchKeyword);

		request.setAttribute("boardList", pd.getList());
		request.setAttribute("pageNavi", pd.getPageNavi());
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
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
