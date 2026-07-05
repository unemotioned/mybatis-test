package kr.or.iei.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private String memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private String memberAddr;
	private int memberLevel;
	private String enrollDate;

	// MyBatis 동적 쿼리 테스트 시 임의 플래그 변수 생성
	private String sFlag1;
	private String sFlag2;
	private String sFlag3;
	private String sFlag4;
	private String sFlag5;
	private String sFlag6;
	private String sFlag7;
	// .setSFlag$(), .getSFlag$()

	private String levelCode;
	private String levelName;
}
