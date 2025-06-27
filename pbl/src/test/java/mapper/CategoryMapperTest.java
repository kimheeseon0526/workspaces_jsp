package mapper;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Category;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class CategoryMapperTest { // 서비스와 매퍼는 테스트코드 쓰는것이 좋음

    private CategoryMapper categoryMapper = MybatisUtil.getSqlSession().getMapper(CategoryMapper.class);



    @Test
    @DisplayName("목록 조회")
    public void testList() {
        List<Category> list = categoryMapper.list();
        list.forEach(b->log.info("{}", b));

    }
    



}