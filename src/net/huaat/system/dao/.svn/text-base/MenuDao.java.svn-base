package net.huaat.system.dao;

import java.util.List;
import net.huaat.system.pojo.TMenu;
import net.huaat.system.pojo.TRoleMenu;

/**  
 * @Description: TODO
 * @author hailin chen  
 * @date 2012-11-17 下午5:08:18
 * @version V1.0  
 */
public interface MenuDao {
	/**
	 * 获取用户菜单
	 * @param id
	 * @return
	 */
	public List<TMenu> getMenuList(String id);
	
	/**
	 * 根据父菜单 ID查询子菜单
	 * @param menuId
	 * @return
	 */
	public List<TMenu> getMenuItemList(String menuId);
	
	/**
	 * 获取全部菜单选择
	 * @param tUser
	 * @return
	 */
	public List<TMenu> getAllMenuItemList();
	
	
	public List<TRoleMenu> getMenuByRoleId(String condition,String id);

	public List<TMenu> getMenuList02(String id, String isLeaf);
}

