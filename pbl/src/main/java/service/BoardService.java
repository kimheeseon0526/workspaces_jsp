package service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import domain.Board;
import mapper.BoardMapper;
import util.MybatisUtil;

public class BoardService {
	public List<Board> list() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.list();
		} catch (Exception e) { 
			e.printStackTrace();
			}
		return null;
	}

	public Board findBy(long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.selectOne();
		} catch (Exception e) { 
			e.printStackTrace();
			}
		return null;
	}

	public void write(Board board) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.insert(board);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

}
