package schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.attach.UploadFile;
import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import util.MybatisUtil;

@Slf4j
public class GhostFileCleanupJob implements Job{
	//당일 파일이 아닌 어제 파일 정리!
//	final String UPLOAD_PATH = "D:/upload/files/";

	@Override
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//File 인스턴스 생성 > 어제 날짜의 업로드 폴더
		File file = new File(UploadFile.UPLOAD_PATH, genYesterdayPath());
		log.info("{}, {}", file, file.exists());
				
		//디렉토리 없으면 멈추고, 있으면 가져오기
		if(!file.exists() || !file.isDirectory()) {
			return ;
		}
//		String[] strings = file.list();
//		File[] files = file.listFiles();
//		log.info("{}", Arrays.toString(strings));
		
//		List<String> fsList = new ArrayList<String>(List.of("a.txt", "b.txt", "c.txt"));
//		List<String> dbList = new ArrayList<String>(List.of("a.txt", "b.txt"));
//		fsList.removeAll(dbList);

		List<File> fsListFiles = new ArrayList<>(Arrays.asList(file.listFiles()));
		SqlSession session = MybatisUtil.getSqlSession();
		
		//현재 이슈
		//dbListFiles에는 thumbnail 파일에 대한 추가가 되지 않음
		//이미지 파일 2개 일반 파일 1개로 구성된 총 3개의 attach라면
		List<Attach> attachs = new ArrayList<>(session.getMapper(AttachMapper.class).selectYesterdayList());
		log.info("==================attachs================");
		attachs.forEach(a -> log.info("{}", a));
		
		
		List<Attach> thumbs = new ArrayList<>(attachs).stream().filter(Attach::isImage).map(Attach::toThumb).toList();
		log.info("==================thumbs================");
		thumbs.forEach(a -> log.info("{}", a));
		
		//이미지 파일 2개 + 썸네일 2개 + 일반 파일 1개로 구성된 총 5개의 attachlist로 변경되어야함
		//체이닝을 통해 한꺼번에 처리하기보단 List<ATTACH> 상태에서 추가 작업 후 추후에 List<File>로 변경하라
		
		attachs.addAll(thumbs);
		
		log.info("==================attachs================");
		attachs.forEach(a -> log.info("{}", a));
		
		
		List<File> dbListFiles = session.getMapper(AttachMapper.class).selectYesterdayList().stream().map(Attach::toFile).toList();
//		List<File> dbListFiles = session.getMapper(AttachMapper.class).selectYesterdayList().stream().map(Attach::toFile).toList();
		log.info("==================dbListFiles================");
		attachs.forEach(a -> log.info("{}", a));
		session.close(); //세션 닫기
		
		
		fsListFiles.removeAll(dbListFiles);
		
		
		log.info("{}", fsListFiles);
		
		
//		fsListFiles.forEach(f -> f.delete());
		//DB상에 있는 애들 삭제
		
	}
	
	
	private String genYesterdayPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}
	
		
	public static void main(String[] args) throws Exception {
		new GhostFileCleanupJob().execute(null);
	}
	
}
	


