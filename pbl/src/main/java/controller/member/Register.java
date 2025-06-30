package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.ParamUtil;

@WebServlet("/member/register")
@Slf4j
public class Register extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);
	}
//브라우저 주소창은 /member/register 유지되고, 실제 응답은 register.jsp 결과로 대체
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1.회원가입시에 올 4가지 파라미터 수집(id,pw,name,email)
//		String id =  req.getParameter("id");
//		String pw =  req.getParameter("pw");
//		String name =  req.getParameter("name");
//		String email =  req.getParameter("email");
		
		//2.Member 인스턴스 생성 -> 입력받은 id, pw, name, email을 member에 담아야하니까
//		Member member = Member.builder().id(id).pw(pw).name(name).email(email).build();
		Member member = ParamUtil.get(req, Member.class);
		log.info("{}" , member);
		//3.service.register(member) 호출
		 new MemberService().register(member);
		//4.index로 리디렉션
		resp.sendRedirect("../index");
	}
	
}
