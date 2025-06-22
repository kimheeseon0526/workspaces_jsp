package service;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberMapper;
import util.MybatisUtil;
import util.PasswordEncoder;
@Slf4j
public class MemberService {
	//회원가입
	public int register(Member member) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			//getMapper(MemberMapper.class) : Membermapper 인터페이스와
			//실제 쿼리문을 작성하는 MemberMapper.xml을 열결해주는 것
			member.setPw(PasswordEncoder.encode(member.getPw()));
			return mapper.insert(member);
			//mapper 인터페이스의 메서드로 sql 실행 가능
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
		memberService.register(Member.builder().id("sae").pw("1234").name("새똥이").build());
		log.info("{}", memberService.login("sae", "1234"));
		memberService.login("sae", "12345");
	}
	
}
