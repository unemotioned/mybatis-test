package kr.or.iei.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	private String rNum;
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String readCount;
	private String regDate;
}
