package controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;



@WebServlet("/board/remove")
@Slf4j
public class Remove extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근!", "/board/list", req, resp);
			//잘못된 접근이라고 alert 뜨고 보드의 리스트로 이동
			return;
		}
		
		//view.jsp에서 cri의 qs,qs2 사용하기 위해 정의해줌
		BoardService service = new BoardService();
		service.remove(Long.valueOf(req.getParameter("bno")));
		Criteria cri = Criteria.init(req);
		req.setAttribute("cri", cri);
		AlertUtil.alert("쓱삭슥싹 삭제 완료", "/board/list?" + cri.getQs2() , req, resp);

	}
	
}
