package service;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberMapper;
import util.MybatisUtil;
import util.PasswordEncoder;
@Slf4j
public class MemberService {
	//
	public int register(Member member) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member.setPw(PasswordEncoder.encode(member.getPw()));
			return mapper.insert(member);
		} catch (Exception e) { 
			e.printStackTrace();
			}
		return 0;
	}
	
	public Member findById(String id) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		} catch (Exception e) { 
			e.printStackTrace();
			}
		return null;
	}
	public boolean login(String id, String pw) {
		Member member = findById(id);
		if(member == null) {
			return false;
		}
		return PasswordEncoder.matches(pw,  member.getPw());
	}
	
	public static void main(String[] args) {
		MemberService memberService = new MemberService();
//		Member member = Member.builder().id("sae1").pw("1234").build();
//		memberService.register(member);
//		log.info("{}", memberService.findById("sae1"));
//		log.info("{} 핫하 {} ", 10, 20);
//		출력값 : [35m11:14:33.332[0;39m [34m INFO[0;39m [36mservice.MemberService[0;39m - 10 핫하 20
		memberService.register(Member.builder().id("sae").pw("1234").name("새똥이").build());
		
		log.info("{}", memberService.login("sae", "1234"));
		memberService.login("sae", "12345");
	}
	
}
