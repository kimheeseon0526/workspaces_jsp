package domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("attach")
public class Attach {

	private String uuid;	//파일 아이디
	private String path;	//경로(날짜별로 파일 관리하기 위한)
	private boolean image;	//파일 존재 여부
	private String origin;	//파일 원본
	private Long bno;
	private Long rno;
	private int odr;
	private long size;
	
	public Attach(String uuid, String path, boolean image, String origin, Long bno, int odr, long size) {
		super();
		this.uuid = uuid;
		this.path = path;
		this.image = image;
		this.origin = origin;
		this.bno = bno;
		this.odr = odr;
		this.size = size;
	}
	

	
	
	
}
