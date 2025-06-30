package controller.member;

import java.io.IOException;

import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.ParamUtil;

@WebServlet("/member/login")
@Slf4j
public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		//http://localhost:8080/member/login
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//login.jsp에서의 입력값을 저장
//		String id = req.getParameter("id");
//		String pw = req.getParameter("pw");
		Member member = ParamUtil.get(req, Member.class);
		boolean ret = new MemberService().login(member.getId(), member.getPw());

		
		if(ret) {//로그인 성공 -> 세션에 유저 정보 저장
			HttpSession session =  req.getSession();
			session.setMaxInactiveInterval(60 * 10); //10분 유지
			session.setAttribute("member", new MemberService().findById(member.getId()));
			
			String url = req.getParameter("url");
			if(url == null) {
				resp.sendRedirect(req.getContextPath() + "/index");
			}
			else {//decode : 인코딩된 문자열을 원래 문자열로 복원(인코딩 : 암호화처럼 바꾸는거)
				String decodeUrl = URLDecoder.decode(url, "utf-8");
				Criteria cri = Criteria.init(req);
				
				resp.sendRedirect(decodeUrl + "?" + cri.getQs2());
			}

			
		}else {//로그인 실패
			resp.sendRedirect("login?msg=login fail");
		}
	}
}
