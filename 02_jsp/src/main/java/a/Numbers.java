package a;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/number")
public class Numbers extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Integer> list = List.of(10, 30, 50, 60);
		req.setAttribute("num", list);	//리스트의 속성값을 num에 새로 추가
		req.getRequestDispatcher("number.jsp").forward(req, resp);
	}
	
	

}
