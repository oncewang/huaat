package net.huaat.system.service.sys;

import java.util.List;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.system.pojo.TUser;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-27 下午5:24:46
 * @version V1.0  
 */
public interface UserService {
	/**
	 * 用户登录验证
	 * @param userName
	 * @param password
	 * @return 1、验证成功<br>2、密码错误<br>3、用户不存在
	 */
	public int loginValidate(String userName,String password);
	/**
	 * 通过用户id得到用户菜单html字符串
	 * @param userId
	 * @return
	 */
	public String getUserMenuHTML(int userId);
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

