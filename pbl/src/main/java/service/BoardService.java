package service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;


import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.BoardMapper;
import util.MybatisUtil;
@Slf4j
public class BoardService {
	public List<Board> list(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			List<Board> list = mapper.list(cri);
			
			return list; //3페이지에 50개씩
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
	
	public long getCount(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCount(cri);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		new BoardService().list(new Criteria()).forEach(b -> log.info("{}", b.getTitle()));
	}
}
