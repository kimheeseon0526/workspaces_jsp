package service;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import mapper.MemberMapper;
import util.MybatisUtil;

public class MemberService {
	//
	public int register(Member member) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
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
	public static void main(String[] args) {
		MemberService memberService = new MemberService();
		Member member = Member.builder().id("sae1").pw("1234").build();
		memberService.register(member);
	}

	
}
