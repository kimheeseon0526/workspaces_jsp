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
			Long bno = board.getBno();
			if(bno == null) {	//답글 아닌 신규글
				mapper.insert(board);
				mapper.updateGrpMyself(board);
				
			}else {	//답글
				//1부모글 조회
				Board parent =  mapper.selectOne(bno);
				Long grp = parent.getGrp();
				int seq = parent.getSeq();
				int depth = parent.getDepth();
				//내 위치에 작성하기 위한 update 처리(위치 재조정)
				
				//2.maxSeq취득
				int maxSeq = mapper.selectMaxSeq(parent);
				board.setSeq(maxSeq + 1);	//수정필요 
				//3.해당 조건의 게시글들의 seq 밀어내기
				board.setGrp(parent.getGrp());	//확정
				board.setDepth(parent.getDepth() + 1);	//확정
				mapper.updateSeqIncrease(board);	//수정필요			
				//4.내 insert
				mapper.insertChild(board);
			}

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
