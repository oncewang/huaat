package net.huaat.system.service.sys;

import java.util.List;

import net.huaat.system.pojo.TRoleMenu;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-27 下午5:24:46
 * @version V1.0  
 */
public interface RoleService {
	/**
	 * 根据roleId删除角色权限
	 * @param roleId
	 * @return 
	 */
	public void deleteByRoleId(String roleId);
	
	
	public List<TRoleMenu> getList(String roleId);
	
	/***
	 * 根据roleId判断是否被使用
	 * @param roleId
	 */
	public boolean isUsedByRoleId(String roleId);

}

