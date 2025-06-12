package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LogTest {
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	public static void main(String[] args) {
		
		//로그 레벨
		logger.debug("디버그 로그");
		logger.info("정보 로그");
		logger.warn("경고 로그");
		logger.error("에러 로그");
		

	}
}
