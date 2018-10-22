package net.huaat.system.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.system.dao.UserDao;
import net.huaat.system.pojo.TUser;
import net.huaat.system.service.sys.UserService;

import org.springframework.stereotype.Service;


/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-27 下午5:26:15
 * @version V1.0  
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public String getUserMenuHTML(int userId) {
		
		return "getUserMenuHTML";
	}

	@Override
	public int loginValidate(String userName, String password) {
		TUser user = userDao.getUserByUserName(userName); 
		if(user==null){
			return 0;
		}else if(!(user.getPassword().equals(password))){
			return 0;
		}
		return 1;
	}

	@Override
	public TUser getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName); 
	}

	@Override
	public List<TUser> getUserList(Pagination pagination, TUser tUser) { 
		// TODO Auto-generated method stub
		return userDao.getUserList(pagination, tUser);
	}

}

