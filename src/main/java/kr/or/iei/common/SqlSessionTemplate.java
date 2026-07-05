package kr.or.iei.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	public static SqlSession getSqlSession() {
		SqlSession session = null; // SQL 문을 실행하고 트랜잭션을 관리하기위한 객체

		String resource = "mybatis-config.xml"; // config 파일 지정

		try {
			// class path(최상위 경로) 의 설정 파일을 읽어오기 위한 스트링 객체 얻어오기
			InputStream is = Resources.getResourceAsStream(resource);

			// SqlSessinFactory 객체를 생성하기 위한 빌더 객체
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// 팩토리 필더를 이용해, 팩토리 객체 생성(SqlSession 객체를 생성하는 역할)
			SqlSessionFactory factory = builder.build(is);

			// SqlSession 객채 생성
			session = factory.openSession(true); // false : autoCommit 하지 않음
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}

}
