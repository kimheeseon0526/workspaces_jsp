package controller.reply;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.ReplyService;
import util.JsonRespUtil;

@WebServlet("/reply/*")
@Slf4j
public class ReplyServlet extends HttpServlet{
	
	public static final String ID = "/reply/";
	
	private String getURI(HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf(ID) + ID.length());
		return uri;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		ReplyService service = new ReplyService();
		Gson gson = new Gson();
		Object o  = null ;
		if(uri.startsWith("list") || uri.equals("*")) { // 목록조회
			log.info("{}", uri);
			String tmp = "list/";
			if(uri.contains(tmp)) {
				String[] tmps = uri.split("/");
				Long bno = null;
				
				if(tmps.length > 1) {
					Long lastRno = null;
					bno = Long.valueOf(tmps[1]);
					if(tmps.length > 2) {
						lastRno = Long.valueOf(tmps[2]);
					}
					o = service.list(bno, lastRno);
				}
			}
		} 
		else { // 단일조회
			o = service.findBy(Long.parseLong(uri));
		}
		
		JsonRespUtil.writeJSON(resp, o);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		new ReplyService().remove(rno);
		
		JsonRespUtil.writeJSON(resp, Map.of("result", true));
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//insert
					
		Reply reply = JsonRespUtil.readJson(req, Reply.class);
		new ReplyService().register(reply);
		JsonRespUtil.writeJSON(resp, Map.of("result", true, "reply", reply));
				
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
			
		Reply reply = JsonRespUtil.readJson(req, Reply.class);
		new ReplyService().modify(reply);
		JsonRespUtil.writeJSON(resp, Map.of("result", true));
		
	}
	
}
