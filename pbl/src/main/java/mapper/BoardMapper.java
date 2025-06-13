package mapper;

import java.util.List;

import domain.Board;

public interface BoardMapper {

	List<Board> list();


	Board selectOne();

}
