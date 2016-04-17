package cn.leepon.demo;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**   
 * This class is used for ...   
 * @author leepon1990  
 * @version   
 *       1.0, 2016年4月17日 下午5:36:26   
 */
public class ScheduleJob {
	
	private static Logger logger = Logger.getLogger(ScheduleJob.class);
	
	private ScheduleJob(){};
	
	// 通过SchedulerFactory获取一个调度器实例
	private synchronized static Scheduler getSchedule(){
		
		Scheduler scheduler = null;
		
		if (scheduler == null) {
			SchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
			try {
				scheduler = stdSchedulerFactory.getScheduler();
			} catch (SchedulerException e) {
				logger.info("获取scheduler实例异常："+e.getMessage()); 
			}
		}
		return scheduler; 
	}
	
	
	public static void scheduleJob(JobDetail jobDetail,Trigger trigger){
		Scheduler schedule = getSchedule();
		try {
			// 注册并进行调度
			schedule.scheduleJob(jobDetail, trigger);
			// 启动调度
			schedule.start();
		} catch (SchedulerException e) {
			logger.info("调度任务启动异常："+ e.getMessage()); 
			e.printStackTrace();
		}
	}

 
}
