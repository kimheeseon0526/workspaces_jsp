package chap01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcServ")
public class CalcServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//콘솔에 가감승제 결과 출력
		int num1 = Integer.parseInt(req.getParameter("value1"));
		int num2 = Integer.parseInt(req.getParameter("value2"));
		
		System.out.println(num1 + num2);
		
//		resp.sendRedirect("calc_form.jsp");
//		페이지 이동(클라이언트에 응답 보낸 후 특정 url로 다시 요청)
		
	}
	 
}
