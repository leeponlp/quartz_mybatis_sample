/**
Copyright (c) 2016. 上海趣医网络科技有限公司 版权所有
Shanghai QuYi Network Technology Co., Ltd. All Rights Reserved.

This is NOT a freeware,use is subject to license terms.
*/

package cn.leepon.quartzdemo;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * This class is used for ...
 * 
 * @author leepon1990
 * @version 1.0, 2016年4月13日 上午10:48:15
 */
@SuppressWarnings("all") 
public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//context.getMergedJobDataMap().get("");
		System.out.println(context.getTrigger().getDescription() + " triggered. time is:" + (new Date()));
	}

	public static void main(String[] args) {
		try {

			// 创建一个JobDetail实例，指定SimpleJob
			JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1")
					.withDescription("simplejob demo").build();
			
			//持有任务的状态信息
			//JobDataMap jobDataMap = jobDetail.getJobDataMap();
			//jobDataMap.put("", ""); 

			// 通过Trigger定义调度规则：马上启动，每2秒运行一次
//			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("trigger1", "group1"))
//					.withDescription("based group1 with trigger1").startAt(new Date())
//					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
//					.build();

			Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("trigger2", "group1"))
					.withDescription("based group1 with trigger2").withSchedule(CronScheduleBuilder.cronSchedule("0 20 11 * * ?"))
					.build();

			// 通过SchedulerFactory获取一个调度器实例

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();

			Scheduler scheduler = schedulerFactory.getScheduler();

			// 注册并进行调度
			scheduler.scheduleJob(jobDetail, trigger2);

			// 启动调度
			scheduler.start();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
