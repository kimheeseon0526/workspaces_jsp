package domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
	// Primary Key
	private Long rno; // Review Number 리뷰번호
	
	private String content; // Content 내용
	private Date regdate; // Registered Date 작성일자
	private Integer rating; // Rating 별점
	
	//Foreign Key
	private String writer; // Writer 작성자
	private Long pno; // Product Number 제품 번호
}
