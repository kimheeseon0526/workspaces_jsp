package controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;



@WebServlet("/board/view")
@Slf4j
public class View extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
	    
//		if(bno == null) {
//			resp.setContentType("text/html; charset=utf-8");
//			PrintWriter pw = resp.getWriter();
//			/* PrintWriter 클래스의 객체를 생성해서 출력으로 사용할 파일을 open한다. */
//			pw.print("<script>");
//			pw.print("alert('잘못된 접근입니다');");
//			pw.print("location.href = 'list' ");
//			pw.print("</script>");
//			return;
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근!", "/board/list", req, resp);
			//잘못된 접근이라고 alert 뜨고 보드의 리스트로 이동
			return;
		}
		
		BoardService service = new BoardService();
		Board board =  service.findBy(Long.parseLong(bno));
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);
	}
	
}
