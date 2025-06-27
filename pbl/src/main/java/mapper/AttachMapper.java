package mapper;

import java.util.List;

import domain.Attach;

public interface AttachMapper {
	//첨부파일 매퍼
	void insert(Attach attach);
	List<Attach> list(Long bno);
	Attach selectOne(String uuid);
	void delete(String uuid);
	void deleteByBno(Long bno);

}
