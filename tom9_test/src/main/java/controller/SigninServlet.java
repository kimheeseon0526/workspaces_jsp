package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.MemberService;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("signin.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		
//		Member member = Member.builder().id(id).pw(pw).name(name).build();
//		new MemberService().register(member);
		
		MemberService service = new MemberService();
		Member member = service.findBy(id);	//아이디로 회원찾기
		if(member == null || !member.getPw().equals(pw)) {
			resp.sendRedirect("signin?msg=failed");
			//회원 없음
		}
		else {
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			resp.sendRedirect("index.jsp");
		}
		
//		resp.sendRedirect("signin");
	}

}
