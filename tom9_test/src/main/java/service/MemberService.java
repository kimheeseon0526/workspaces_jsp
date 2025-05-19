package service;

import dao.MemberDao;
import domain.Member;

public class MemberService {
	//호출
	private MemberDao memberDao = new MemberDao();
	
	//기능 구현
	//회원 가입
	public void register(Member member) {
		memberDao.insert(member);
	}
	
	//회원 조회
	public Member findBy(String id) {
		return memberDao.selectOne(id);
	}
}
