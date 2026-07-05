package kr.or.iei.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import kr.or.iei.board.model.dao.BoardDao;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardPageData;
import kr.or.iei.common.SqlSessionTemplate;

public class BoardService {

	private BoardDao dao;

	public BoardService() {
		super();
		dao = new BoardDao();
	}

	public BoardPageData selectAllBoardList(int reqPage, String searchType, String searchKeyword) {
		SqlSession session = SqlSessionTemplate.getSqlSession();

		int viewBoardCnt = 10;
		int end = reqPage * viewBoardCnt;
		int start = end - viewBoardCnt + 1;

		// String, Integer 에서 검색어도 전달하기 위해서 String 으로 변경
		HashMap<String, String> map = new HashMap<>();
		// 변수의 값을 String 으로 변환시켜줌
		map.put("start", String.valueOf(start));
		// 정수와 문자열을 결합 하면 문자열이 된다
		map.put("end", end + "");
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);

		ArrayList<Board> list = (ArrayList<Board>) dao.selectBoradList(session, map);

		/*
		 * 사용자가 조건 입력시 조건에 부합하는 게시글의 갯수를 조회
		 */
		int totCnt = dao.selectTotalCount(session, map);
		int totPage = 0;

		totPage = totCnt / viewBoardCnt;

		if (totCnt % viewBoardCnt != 0) {
			totPage += 1;
		}

		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		String pageNavi = "";
		String searchParam = "";

		if (searchKeyword != null) {
			searchParam += "&searchType=" + searchType + "&searchKeyword=" + searchKeyword;
		}

		if (pageNo != 1) {
			pageNavi += "<a href='/board/getList?reqPage=" + (pageNo - 1) + searchParam + "'>이전</a>";
		}

		for (int i = 0; i < pageNaviSize; i++) {
			pageNavi += "<a href='/board/getList?reqPage=" + pageNo + searchParam + "'>" + pageNo + "</a>";
			pageNo++;

			if (pageNo > totPage) {
				break;
			}
		}

		if (pageNo <= totPage) {
			pageNavi += "<a href='/board/getList?reqPage=" + pageNo + searchParam + "'>다음</a>";
		}

		BoardPageData pd = new BoardPageData();
		pd.setList(list);
		pd.setPageNavi(pageNavi);

		session.close();
		return pd;
	}

	public int deleteBoard(String[] delNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.deleteBoard(session, delNo);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public Board selectOneBoard(String boardNo, String pageGb) {
		SqlSession session = SqlSessionTemplate.getSqlSession();

		int result = 1;

		// 게시글 목록에서 제목을 클릭하여 상세보기 페이지로 이동 시: pageGb == select
		// 게시글 상세보기에서 수정하기 페이지로 이동 시: pageGb == update
		// 수정하기 페이지에서 수정 완료후 상세보기로 이동시: pageGb == update
		if (pageGb.equals("select")) {
			result = dao.updateReadCount(session, boardNo);

			if (result > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		}

		Board board = null;

		// result 가 1 인 경우 (select 이고, 정상 업데이트 되었을때 또는 update 일때)
		if (result > 0) {
			board = dao.selectOneBoard(session, boardNo);
		}

		session.close();
		return board;
	}

	public int updateBoard(Board board) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.updateBoard(session, board);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

}
