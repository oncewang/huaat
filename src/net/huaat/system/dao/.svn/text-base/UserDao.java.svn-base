package net.huaat.system.dao;

import java.util.List;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.system.pojo.TUser;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-10-8 下午3:50:08
 * @version V1.0  
 */
public interface UserDao {
	
	/**
	 * 通过用户名取得TUser对象
	 * @param userName
	 * @return
	 */
	public TUser getUserByUserName(String userName);
	/**
	 * 按条件分页查询用户列表
	 * @param pagination
	 * @param tUser
	 * @return
	 */
	public List<TUser> getUserList(Pagination pagination, TUser tUser);

}

