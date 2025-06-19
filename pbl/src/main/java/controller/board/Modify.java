package controller.board;

import java.io.IOException;

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
@Slf4j
@WebServlet("/board/modify")
public class Modify extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = Criteria.init(req);
		//session 내의 member attr 조회 후 null
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근!", "/board/list", req, resp);
			//잘못된 접근이라고 alert 뜨고 보드의 리스트로 이동
			return;
		}
		
		Long bno = Long.valueOf(req.getParameter("bno"));
		
		
		if(req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인 후 글 작성하슈", "/member/login?bno=" + bno +"&" + cri.getQs2(), req, resp, true);
			return;
		}
		
		
		//view.jsp에서 cri의 qs,qs2 사용하기 위해 정의해줌
		BoardService service = new BoardService();
		Board board = service.findBy(Long.parseLong(req.getParameter("bno")));
		req.setAttribute("cri", cri);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = Criteria.init(req);
		//session 내의 member attr 조회 후 null
		if(req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인 후 글 작성하슈", "/member/login?" + cri.getQs2(), req, resp, true);
			return;
		}
//		
//		// 파라미터 수집
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		Integer cno = cri.getCno();
		Long bno = Long.valueOf(req.getParameter("bno"));
//		//board 인스턴스 생성(4개)
		Board board = Board.builder().title(title).content(content).cno(cno).bno(bno).build();
//		log.info("{}", board);
//		
//		//서비스 호출(board 객체가지고)
		new BoardService().modify(board);
//		
//		//리디렉션(board/list)
		AlertUtil.alert("글 수정 완료", "/board/view?bno=" + bno + "&" + cri.getQs2() , req, resp);
//		//글 작성 완료 후 1페이지로 돌아가~!
	}
}
