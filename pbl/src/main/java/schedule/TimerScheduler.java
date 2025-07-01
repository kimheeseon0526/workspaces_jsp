package schedule;


import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TimerScheduler {
	public static void main(String[] args) throws Exception {
		String cronExpression = "0/5 * * * * ?";	//초 분 시 일 월
		//1. 스케줄러 생성
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//2. Job 등록
		JobDetail job = JobBuilder.newJob(TimerJob.class).withIdentity("timerJob", "grp1").build();
		//3. Trigger 설정(호출 횟수 빈도)
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("timerTrigger", "grp1")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();
				
		//4. 스케줄러에 Job, Trigger 등록
		scheduler.scheduleJob(job, trigger);
		//5.테스트용 60초간 동작후 스케줄러 마무리
		scheduler.start();
		Thread.sleep(60 * 1000);
		scheduler.shutdown();
	}
}
