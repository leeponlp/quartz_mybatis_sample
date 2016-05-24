package cn.leepon.demo;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

/**   
 * This class is used for ...   
 * @author leepon1990  
 * @version   
 *       1.0, 2016年4月17日 下午5:34:16   
 */
public class SimpleTrigger {
	
	public static void main(String[] args) {
		
		
		
		// 创建一个JobDetail实例，指定MyJob
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1")
			.withDescription("simpletrigger_job_demo").build();
		
 		
		
		//持有任务的状态信息
		//JobDataMap jobDataMap = jobDetail.getJobDataMap();
		//jobDataMap.put("description", "simpletrigger_job_demo"); 
		
		// 通过Trigger定义调度规则：马上启动，每2秒运行一次
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("trigger1", "group1"))
				.withDescription("based group1 with trigger1").startAt(new Date())
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();
		
		ScheduleJob.scheduleJob(jobDetail, trigger); 
		
		
	}

}
