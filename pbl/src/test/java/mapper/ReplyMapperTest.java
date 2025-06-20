package mapper;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class ReplyMapperTest { // 서비스와 매퍼는 테스트코드 쓰는것이 좋음

    private ReplyMapper replyMapper = MybatisUtil.getSqlSession().getMapper(ReplyMapper.class);

    @Test
    @DisplayName("단일 조회")
    public void testSelectOne() {
        Long rno = 7L;

        Reply reply = replyMapper.selectOne(rno);

        log.info("{}", reply);
    }


    @Test
    @DisplayName("목록 조회")
    public void testList() {
        List<Reply> list = replyMapper.list(1L, 35L);
        list.forEach(b->log.info(b.getContent()));

    }
    

    @Test
    @DisplayName("목록 조회-lastRno null일때")
    public void testListisRnoNull() {
        List<Reply> list = replyMapper.list(4092L, null);
        list.forEach(b->log.info("{}", b.getRegdate()));

    }
//
    @Test
    @DisplayName("댓글 추가 테스트")
    public void addUpdate() {
        Reply reply = Reply.builder().content("매퍼 테스트").id("qwe").bno(1L).build();
        replyMapper.insert(reply);
    }

    @Test
    @DisplayName("댓글수정 테스트")
    public void testUpdate() {

        Long rno = 1L;
        Reply reply = replyMapper.selectOne(rno);
        reply.setContent("수정하기");
        replyMapper.update(reply);
    }

    @Test
    @DisplayName("댓글삭제 테스트")
    public void testdelete() {

        Long rno = 4L;
        replyMapper.delete(rno);


    }
}