package kr.or.iei.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import kr.or.iei.common.SqlSessionTemplate;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberPageData;

public class MemberService {

	private MemberDao dao;

	public MemberService() {
		super();
		dao = new MemberDao();
	}

	public Member selectOneMember(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member loginMember = dao.selectOneMember(session, member);
		session.close();
		return loginMember;
	}

	public int insertMember(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.insertMember(session, member);

		if (result > 0) {
			session.commit();
		} else {
			// 하나만 할 경우에는 rollback 하지 않아도 된다
			session.rollback();
		}

		session.close();
		return result;
	}

	public ArrayList<Member> selectAllMember() {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		// Down casting
		ArrayList<Member> memberList = (ArrayList<Member>) dao.selectAllMember(session);
		session.close();
		return memberList;
	}

	public int updateMember(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.updateMember(session, member);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();
		return result;
	}

	public int deleteMember(String memberNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.deleteMember(session, memberNo);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();
		return result;
	}

	public MemberPageData selectAllMemberPage(int reqPage) {
		SqlSession session = SqlSessionTemplate.getSqlSession();

		int viewMemberCnt = 10;
		int end = reqPage * viewMemberCnt;
		int start = end - viewMemberCnt + 1;

		// dao 에서 xml 퀴리 호출하며, 전달할 수 있는 파라미터는 쿼리ID 를 제외하고 1개 이므로
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		ArrayList<Member> list = (ArrayList<Member>) dao.selectAllMemberPage(session, map);

		int totCnt = dao.selectTotalCount(session);
		int totPage = 0;

		totPage = totCnt / viewMemberCnt;

		if (totCnt % viewMemberCnt != 0) {
			totPage += 1;
		}

		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		String pageNavi = "";

		if (pageNo != 1) {
			pageNavi += "<a href='/member/allMemberPage?reqPage=" + (pageNo - 1) + "'>이전</a>";
		}

		for (int i = 0; i < pageNaviSize; i++) {
			pageNavi += "<a href='/member/allMemberPage?reqPage=" + pageNo + "'>" + pageNo + "</a>";
			pageNo++;

			if (pageNo > totPage) {
				break;
			}
		}

		if (pageNo <= totPage) {
			pageNavi += "<a href='/member/allMemberPage?reqPage=" + (pageNo + 1) + "'>다음</a>";
		}

		MemberPageData pd = new MemberPageData();
		pd.setList(list);
		pd.setPageNavi(pageNavi);

		session.close();
		return pd;
	}

	public int chgMemberLevel(String memberNo, String memberLevel) {
		SqlSession session = SqlSessionTemplate.getSqlSession();

		Member member = new Member();
		member.setMemberNo(memberNo);
		member.setMemberLevel(Integer.parseInt(memberLevel));

		int result = dao.chgMemberLevel(session, member);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();
		return result;
	}

	public ArrayList<Member> selDynamicIfTest(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		ArrayList<Member> list = (ArrayList<Member>) dao.selDynamicIfTest(session, member);
		session.close();
		return list;
	}

	public ArrayList<Member> selDynamicForTest(String[] members) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		ArrayList<Member> list = (ArrayList<Member>) dao.selDynamicForTest(session, members);
		session.close();
		return list;
	}

	public ArrayList<Member> selDynamicChooseTest(String select, String keyword) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("select", select);
		map.put("keyword", keyword);
		ArrayList<Member> list = (ArrayList<Member>) dao.selDynamicChooseTest(session, map);
		session.close();
		return list;
	}

	public ArrayList<Member> selDynamicTest1(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		ArrayList<Member> list = (ArrayList<Member>) dao.selDynamicTest1(session, member);
		session.close();
		return list;
	}

	public ArrayList<Member> selDynamicTest2(String sFlag1) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		ArrayList<Member> list = (ArrayList<Member>) dao.selDynamicTest2(session, sFlag1);
		session.close();
		return list;
	}

	public ArrayList<Member> selDynamicTest3(Member member) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		ArrayList<Member> list = (ArrayList<Member>) dao.selDynamicTest3(session, member);
		session.close();
		return list;
	}

}
