package domain.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageDto {
	private Criteria cri;
	private long total;
	//총 게시글 수가 있어야 아래 계산 가능
	
	private int start;	
	private int end;	
	
	private boolean left;
	private boolean right;
	
	private boolean doubleLeft; //pagination (<<)
	private boolean doubleRight ;//pagination (>>)
	
	private int realEnd;
	
	public PageDto(Criteria cri, long total) {
		this.cri = cri;
		this.total = total;

		//total = 123
		//page = 1
		//amount = 10
		//start = 1
		//end = 10
		
		//total = 123
		//page = 12 -> total / amount
		//amount = 10
		//start = 11 page - 1
		//end = 13 page + 1

// 		119 12
//		120 12
//		121 13
//		122 13
		
		end = (cri.getPage() + 9) / 10 * 10;
		start = end - 9;
		
		
		//게시글 수 123 한페이지에 20개 -> 7개
		realEnd = (int)((total + cri.getAmount() - 1) / cri.getAmount());
		
		if(end >realEnd) {
			end = realEnd;
		}
		left = start > 1;  //???
		right = end < realEnd;
		
		doubleLeft = cri.getPage() > 1;	//1페이지가 아니면 다 보여줌
		doubleRight = cri.getPage() < realEnd;	//마지막 페이지가 아니면 다 보여줌
	
	}

}

