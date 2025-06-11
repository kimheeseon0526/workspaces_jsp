package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import domain.Member;
import domain.Review;

public interface MemberMapper {

	List<Member> select();
	
	Member findByNo(Long no);
	Member findById(String id);
	

	int insert(Member member);
	
	int delete(Long no);

	int update(Member member);
	
	
}
