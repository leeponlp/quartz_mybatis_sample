package cn.leepon.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.leepon.annotation.Description;
import cn.leepon.aop.DemoJobAspect;
import cn.leepon.mapper.UserInfoMapper;
import cn.leepon.po.UserInfo;

/**   
 * This class is used for ...   
 * @author leepon1990  
 * @version   
 *       1.0, 2016年4月8日 下午3:13:53   
 */
@SuppressWarnings("all") 
public class DemoJob {
	
	private static Logger logger = Logger.getLogger(DemoJob.class);
	
	//@Autowired
	//UserInfoMapper userInfoMapper;
	
	@Description(description="测试用例方法")
	public void demoMethod(){
		
		logger.info("测试用例输出："+ System.currentTimeMillis()/1000); 
		
		//UserInfo userInfo = userInfoMapper.getUserInfoById(1);
		
		//System.err.println(System.currentTimeMillis()/1000+"-->"+userInfo);
	
	}

}
