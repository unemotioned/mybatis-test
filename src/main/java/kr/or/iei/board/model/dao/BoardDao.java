package kr.or.iei.board.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.iei.board.model.vo.Board;

public class BoardDao {

	public List<Board> selectBoradList(SqlSession session, HashMap<String, String> map) {
		return session.selectList("board.selectBoarderList", map);
	}

	public int selectTotalCount(SqlSession session, HashMap<String, String> map) {
		return session.selectOne("board.selectTotalCount", map);
	}

	public int deleteBoard(SqlSession session, String[] delNo) {
		return session.delete("board.deleteBoard", delNo);
	}

	public Board selectOneBoard(SqlSession session, String boardNo) {
		return session.selectOne("board.selectOneBoard", boardNo);
	}

	public int updateReadCount(SqlSession session, String boardNo) {
		return session.update("board.updateReadCount", boardNo);
	}

	public int updateBoard(SqlSession session, Board board) {
		return session.update("board.updateBoard", board);
	}

}
