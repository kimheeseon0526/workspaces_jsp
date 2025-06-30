package mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;


@Slf4j
public class BoardMapperTest {
	
	private BoardMapper boardMapper = MybatisUtil.getSqlSession().getMapper(BoardMapper.class);
	//이게 뭔말...?
	
	@Test
	public void addTest() {
		int result = 1 + 1;
		assertEquals(2, result);
	}
	
	@Test
	public void testSelectOne() {
		//given
		Long bno = 4096L;
		
		//when
		Board board = boardMapper.selectOne(bno);
		
		//then ~ actual, expect
		assertNotNull(board);
		
		log.info("{}", board);
	}
	
	@Test
    @DisplayName("목록 조회 3페이지 10개씩 2번 카테고리 테스트")
    public void testList() {

        Criteria cri = new Criteria(1, 10, 2);  //1페이지 10개, 2번 카테고리
        // 오늘이라는 키워드를 1페이지에 10개씩 카테고리에 보이게 하겠다
        log.info(Arrays.toString(cri.getTypes()));
        List<Board> list = boardMapper.list(cri);
        list.forEach(b -> log.info("{} {} {}", b.getAttachCnt(),b.getReplyCnt(), b.getAttachs()));
    }
	
	@Test
	@DisplayName("목록 조회 검색어 테스트")
	public void testList2() {
		Criteria cri = new Criteria(3, 10, 2, "TI", "새똥");
		//T(제목)이새똥인 3번 페이지에서 ~
		log.info(Arrays.toString(cri.getTypes()));
		List<Board> list = boardMapper.list(cri);
//		list.forEach(b -> log.info("{}", b.getTitle()));
	}
	
	@Test
	@DisplayName("글 수정 테스트")
	public void testUpdate() {
		Long bno = 50L;
		Board board = boardMapper.selectOne(bno);
		board.setTitle("제목만 수정수정");
		
		boardMapper.update(board);
	
	}
	@Test
	@DisplayName("maxSeq 조회")
	public void testSelectMaxSeq() {
		Board parent =  boardMapper.selectOne(4127L);
		int maxSeq = boardMapper.selectMaxSeq(parent);
		log.info("{}", maxSeq);
	}
	



}
