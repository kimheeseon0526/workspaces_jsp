package mapper;

import java.util.List;

import domain.Board;
import domain.dto.Criteria;

public interface BoardMapper {

	List<Board> list(Criteria cri);


	void insert(Board board);
	
	long getCount(Criteria cri);

	Board selectOne(Long bno);

	void update(Board board);

	void delete(Long bno);
	
	void increaseCnt(Long bno);
	
	void updateGrpMyself(Board board);
	
	void updateSeqIncrease(Board parent);
	
	void insertChild(Board board);
	
	int selectMaxSeq(Board parent);

}
