package mapper;

import java.util.List;

import domain.Board;
import domain.dto.Criteria;

public interface BoardMapper {

	List<Board> list(Criteria cri);

	Board selectOne();

	void insert(Board board);
	
	long getCount(Criteria cri);

}
