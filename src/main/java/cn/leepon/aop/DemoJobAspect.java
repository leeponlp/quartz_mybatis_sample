package cn.leepon.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import cn.leepon.annotation.Description;

@SuppressWarnings("all")
@Aspect
public class DemoJobAspect{
	
	private static Logger logger = Logger.getLogger(DemoJobAspect.class);
	
    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	//@Pointcut("execution(* cn.leepon.job.*(..))")
	//public void aspect(){	}
	
	
	
	/**
	 * 前置通知 在目标方法之前执行
	 * @param joinPoint
	 */
	@Before(value = "execution(* cn.leepon.job.DemoJob.demoMethod(..))") 
	public void beforeMethod(JoinPoint joinPoint){
		
		//预处理校验逻辑
		//====
		logger.info("预处理部分校验逻辑执行...");
		Map<String, Object> jobInfo = getJobInfo(joinPoint);
		System.out.println("通过Map.keySet遍历key和value：");
		  for (String key : jobInfo.keySet()) {
		   System.out.println("key= "+ key + " and value= " + jobInfo.get(key));
		  }
	}
	
	/**
	 * 切面的后置方法 不管抛不抛出异常都会在目标方法执行之后执行
	 * @param joinPoint
	 */
	//@After(value = "execution(* cn.leepon.job.DemoJob.demoMethod(..))") 
	public void afterMethod(JoinPoint joinPoint){
		String methodname = joinPoint.getSignature().getName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date());
		System.err.println(date+"后置通知监控方法"+methodname+"执行完成!!!");
	}
	
	/**
	 * 返回通知 返回目标方法的返回值
	 * @param joinPoint
	 * @param result
	 */
	//@AfterReturning(value = "execution(* cn.leepon.job.DemoJob.demoMethod(..))",returning = "result")
	public void afterReturnMethod(JoinPoint joinPoint,Object result){
		String methodname = joinPoint.getSignature().getName();
		System.err.println("返回通知监控方法"+methodname+"执行返回结果为"+result);
	}
	/**
	 * 环绕通知必须要有返回值
	 * @param pjp
	 * @throws Throwable
	 */
	//@Around(value = "execution(* cn.leepon.job.DemoJob.demoMethod(..))")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable{
		long begin = System.currentTimeMillis();
		Object object = pjp.proceed();
		long end = System.currentTimeMillis();
		System.err.println("环绕通知监控方法"+pjp.getSignature().getName()+"执行时间为："+(end-begin)/1000+"秒");
		return object;
	}

	/**
	 * 目标方法执行过程中抛出异常时会执行异常通知
	 * 获得异常信息可以用throwing
	 * @param joinPoint
	 * @param ex
	 */
	//@AfterThrowing(value="execution(* cn.leepon.job.DemoJob.demoMethod(..))", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint,Exception ex){
		String jobname = joinPoint.getSignature().getName();
		String message = ex.getMessage();
		System.err.println("异常通知监控方法"+jobname+"抛出的异常信息是："+message); 
	}
	
	 /** 
     * 获取注解中对方法的描述信息
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
     private  Map<String, Object> getJobInfo(JoinPoint joinPoint){ 
    	Map<String, Object> jobinfo = new HashMap<>();
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class<?> targetClass = null;
		try {
			targetClass = Class.forName(targetName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
         for (Method method : methods) {  
             if (method.getName().equals(methodName)) {  
                Class<?>[] clazzs = method.getParameterTypes();  
                 if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(Description.class).description();  
                     break;  
                }  
            }  
        }  
         jobinfo.put("targetName", targetName);
         jobinfo.put("methodName", methodName);
         jobinfo.put("description", description);
         return jobinfo;  
    }
}
