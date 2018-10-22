package net.huaat.system.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import net.huaat.common.util.StringUtil;
import net.huaat.system.dao.RoleDao;
import net.huaat.system.pojo.TRoleMenu;
import net.huaat.system.pojo.TUser;
import net.huaat.system.service.sys.RoleService;

import org.springframework.stereotype.Service;


/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-27 下午5:26:15
 * @version V1.0  
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	public void deleteByRoleId(String roleId){
		List<TRoleMenu> list = getList(roleId);
		if(list!=null&&list.size()>0){
			roleDao.deleteByRoleId(roleId);
		}
	}
	
	public List<TRoleMenu> getList(String roleId){
		return roleDao.getList(roleId);
	}
	
	public  boolean isUsedByRoleId(String roleId){
		List list = roleDao.getUserList(roleId);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
}

