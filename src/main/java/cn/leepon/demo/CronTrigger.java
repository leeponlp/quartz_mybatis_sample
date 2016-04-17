package cn.leepon.demo;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

/**
 * This class is used for ...
 * 
 * @author leepon1990
 * @version 1.0, 2016年4月17日 下午5:53:22
 */
public class CronTrigger {

	public static void main(String[] args) {
		// 创建一个JobDetail实例，指定MyJob
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1")
				                                            .withDescription("crontrigger_job_demo").build();

		// 持有任务的状态信息
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		jobDataMap.put("description", "crontrigger_job_demo");
		
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("trigger2", "group1"))
				.withDescription("based group1 with trigger2").withSchedule(CronScheduleBuilder.cronSchedule("0 07 18 * * ?"))
				.build();
		ScheduleJob.scheduleJob(jobDetail, trigger); 
		
	}

}
