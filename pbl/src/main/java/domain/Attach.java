package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attach {

	private String uuid;	//파일 아이디
	private String path;	//경로(날짜별로 파일 관리하기 위한)
	private boolean image;	//파일 존재 여부
	private String origin;	//파일 원본
	private Long bno;
	private Long rno;
	
}
