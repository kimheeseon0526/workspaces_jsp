package chap01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/01_servlet/Servlet2 or http://localhost:8080/01_servlet/SecondServlet - 브라우저(서버)에서
@WebServlet(value = {"/SecondServlet","/Servlet2"})
public class SecondServlet extends HttpServlet{

	//text/html, text/plain, text/xml, application/json
	//image/png
	//mime-type(Multipurpose Internet Mail Extensions 또는 MIME type)
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//기본 응답 형태 : text/plain
		resp.setContentType("text/html; charset=euc-kr"); 
		PrintWriter out = resp.getWriter();
		out.write("이런것도 됨 개꿀");
		System.out.println("서버 콘솔에 출력");
		out.println("<h1>화면에 출력 abcd</h1>");
	}
	

}
