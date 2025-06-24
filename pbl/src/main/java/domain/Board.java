package domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("board")
public class Board {

	private Long bno;	//글번호
	private String title;
	private String content;
	private String id;
	private String regdate;	//등록날짜
	private String moddate;	//수정날짜
	private Integer cnt;	//조회수
	private Integer cno;
	
	
	
	public Board(Long bno, String title, String content, String id, String regdate, String moddate, Integer cnt,
			Integer cno) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.moddate = moddate;
		this.cnt = cnt;
		this.cno = cno;
		
	}



	@Builder.Default
	private List<Attach> attachs = new ArrayList<Attach>();
	
}
