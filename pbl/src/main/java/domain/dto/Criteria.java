package domain.dto;

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
	private String type ; //TCI
	private String keyword;

//1$10 > 0 
//2$10 > 10
//3$>10 >20
//4$>10 >30

	
	public int getOffset() {
		int offset = amount * (page -1);
		return offset;
	}
	
	public String[] getTypes() {
		String[] arr = null;
		if(type != null) {
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
		cri.keyword = req.getParameter("keyword");
				
		}
		catch(Exception e) {}
		return cri;
	}
}
