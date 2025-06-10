package chap01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet{
	private int initCnt, servCnt, destCnt;
	
	@Override
	public void init() throws ServletException {
		initCnt++;
		System.out.println("init()");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		servCnt++;
		printCnts();
		System.out.println("service()");
	}

	@Override
	public void destroy() {
		destCnt++;
		System.out.println("destroy()");
	}
	
	void printCnts() {
		System.out.printf("%d %d %d\n", initCnt, servCnt, destCnt);
	}
	
}
