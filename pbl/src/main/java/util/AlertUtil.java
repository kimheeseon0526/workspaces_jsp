package util;

import java.io.IOException;import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlertUtil {
	//알림만 하고 끄는 경우
	
	//알림하고 페이지 이동 시켜야하는 경우
	
	public static void alert(String msg, String url, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", req.getContextPath() + url);
		//걍 받아서 냅다 던져버림(alert.jsp로.....? 어디로...?)
		req.getRequestDispatcher("/WEB-INF/views/common/alert.jsp").forward(req, resp);
		
	}
	public static void alert(String msg, String url, HttpServletRequest req, HttpServletResponse resp, boolean isUrl) throws IOException, ServletException {
		if(isUrl) {
			url = url + "?url=" + URLEncoder.encode(req.getRequestURL().toString(), "utf-8");
		}
		alert(msg, url,req, resp);
		
	}
}
