package domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Board {

	private Long bno;	//글번호
	private String title;
	private String content;
	private String id;
	private String regdate;	//등록날짜
	private String moddate;	//수정날짜
	private Integer cnt;	//조회수
	
}
