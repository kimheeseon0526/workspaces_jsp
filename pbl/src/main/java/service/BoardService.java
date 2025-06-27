package service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;


import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import mapper.BoardMapper;
import mapper.ReplyMapper;
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
			mapper.increaseCnt(bno);
			Board board = mapper.selectOne(bno);
			return board;
		} catch (Exception e) { 
			e.printStackTrace();
			}
		return null;
	}

	public void write(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.insert(board);
			AttachMapper attachmapper = session.getMapper(AttachMapper.class);
			board.getAttachs().forEach(a -> {	//board안에 있는 attachs list 가져와서 그 안에서 bno 추출
				a.setBno(board.getBno());
				attachmapper.insert(a);
			});
			session.commit();
		} catch (Exception e) { 
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
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
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.update(board);
			
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			//기존 첨부파일 메타데이터 제거
			attachMapper.deleteByBno(board.getBno());
			
			//새로 첨부파일 메타데이터 등록
			board.getAttachs().forEach(a -> {	//board안에 있는 attachs list 가져와서 그 안에서 bno 추출
				a.setBno(board.getBno());
				attachMapper.insert(a);
			});
			session.commit();
		} catch (Exception e) { 
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	

	public void remove(long bno) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			ReplyMapper replyMapper = session.getMapper(ReplyMapper.class);
			
			replyMapper.deleteByBno(bno);
			attachMapper.deleteByBno(bno);
			mapper.delete(bno);
			
			session.commit();
		} catch (Exception e) { 
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		

	}


}
