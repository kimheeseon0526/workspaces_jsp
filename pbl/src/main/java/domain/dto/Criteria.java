package domain.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
	private int page = 1;
	private int amount = 10;
	private int cno = 2;
	private String type = ""; //TCI
	private String keyword = "";

//1$10 > 0 
//2$10 > 10
//3$>10 >20
//4$>10 >30
	public Criteria(int i, int j, int k) {
			
		}
	
	public int getOffset() {
		int offset = amount * (page -1);
		return offset;
	}
	
//	public String getKeyword() {
//		String keyword =  null;
//		try {
//			keyword = URLDecoder.decode(keyword, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return keyword;
//	}
	
	public String[] getTypes() {
		String[] arr = null;
		if(type != null && !type.equals("")) {
			arr = type.split("");
		}
		return arr;
	}

	public static Criteria init(HttpServletRequest req) {
		Criteria cri = new Criteria();	
		try {
		cri.cno = Integer.parseInt(req.getParameter("cno"));
		cri.page = Integer.parseInt(req.getParameter("page"));
		cri.amount = Integer.parseInt(req.getParameter("amount"));
		cri.type = req.getParameter("type");
		cri.keyword = URLDecoder.decode(req.getParameter("keyword"), "utf-8");
				
		}
		catch(Exception e) {}
		return cri;
	}
	
	//문자열 반환하는 메서드
	public String getQs() {
		String[] strs = {"cno=" + cno, "amount=" + amount, "type=" + type, "keyword=" + keyword};
		String str = String.join("&", strs);
		return str;
	}
	
	//게시글 상세보기, 글작성, 글삭제에 사용될 예정~!
	public String getQs2() {
		return getQs() + "&page" + page;
	}

	
}
