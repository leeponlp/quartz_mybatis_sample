package cn.leepon.job;

import org.springframework.beans.factory.annotation.Autowired;

import cn.leepon.mapper.UserInfoMapper;
import cn.leepon.po.UserInfo;

/**   
 * This class is used for ...   
 * @author leepon1990  
 * @version   
 *       1.0, 2016年4月8日 下午3:13:53   
 */
public class DemoJob {
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	public void demoMethod(){
		
		UserInfo userInfo = userInfoMapper.getUserInfoById(1);
		
		System.err.println("测试用例启动..."+System.currentTimeMillis()/1000+"-->"+userInfo);
	
	}

}
