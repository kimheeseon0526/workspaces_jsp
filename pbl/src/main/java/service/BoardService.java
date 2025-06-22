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
	//criteria 조건에 맞는 board 객체들을 리스트로 가져옴
	//즉 , criteria cri 는 조건을 넘기고 그 조건에 맞는 board 들을 DB에서 조회해서 list에 담는다
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			List<Board> list = mapper.list(cri);
			
			return list;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}

	public Board findBy(long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.selectOne(bno);
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
	
	//criteria 조건에 맞는 총 게시글 수 조회
	public long getCount(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCount(cri);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void modify(Board board) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.update(board);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
	}
	

	public void remove(long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.delete(bno);
		} catch (Exception e) { 
			e.printStackTrace();
			}

	}


}
