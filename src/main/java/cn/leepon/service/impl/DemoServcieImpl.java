package cn.leepon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cn.leepon.mapper.UserInfoMapper;
import cn.leepon.po.UserInfo;
import cn.leepon.service.DemoService;

/**   
 * This class is used for ...   
 * @author leepon1990  
 * @version   
 *       1.0, 2016年4月18日 上午9:40:09   
 */

@Service
public class DemoServcieImpl implements DemoService{
	
	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	@Scheduled(cron="0 14 10 * * ?")
	public void getUserInfoById() {
		UserInfo userInfo = userInfoMapper.getUserInfoById(1);
		System.err.println(userInfo.toString()); 
	}

}
