package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Review;
import mapper.ReviewMapper;
import util.MybatisUtil;

public class ReviewService {
	
//	public static void main(String[] args) {
//		ReviewService service = new ReviewService();
//		service.register(Review.builder().content("Service Text").pno(1L).rating(5).writer("새똥이").build());
//		System.out.println(service.findBy(1L));
//		System.out.println("=================");
//		service.List().forEach(System.out::println);
//		System.out.println("=================");
//		Review review = service.findBy(1L);
//		review.setContent("Modified Content");
//		
//		service.modify(review);
//		System.out.println(service.findBy(1L));
//		
//		System.out.println(service.findBy(3L));
//		service.remove(3L);
//		System.out.println(service.findBy(3L));
//	}
	
	public Review findBy(Long rno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.findBy(rno);
		} catch (Exception e) { e.printStackTrace();}
		return null;
	}
	public List<Review> List() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.select();
		} catch (Exception e) { e.printStackTrace();}
		return null;
	}
	public int register(Review review) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.insert(review);
		} catch (Exception e) { e.printStackTrace();}
		return 0;
	}
	public int remove(Long rno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.remove(rno);
		} catch (Exception e) { e.printStackTrace();}
		return 0;
	}
	public int modify(Review review) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReviewMapper mapper = session.getMapper(ReviewMapper.class);
			return mapper.update(review);
		} catch (Exception e) { e.printStackTrace();}
		return 0;
	}
}
