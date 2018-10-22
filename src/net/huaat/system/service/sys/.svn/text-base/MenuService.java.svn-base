package net.huaat.system.service.sys;

import java.util.List;
import net.huaat.system.pojo.TMenu;


/**  
 * @Description: TODO
 * @author hialin chen 
 * @date 2012-11-17 下午4:20:46
 * @version V1.0  
 */
public interface MenuService {	
	/**
	 * 获取用户菜单
	 * @param list
	 * @param path
	 */
	public String getUserMenuHTML(List<String> list,String path);
	/**
	 * 获取用户tabs及菜单
	 * @param list
	 * @param path
	 */
	public String getUserTabsHTML(List<String> list,String path);
	
	/**
	 * 获取上级菜单
	 *
	 */
	public List<TMenu> getParentMenu(String id);
	
	/**
	 * 获取全部子菜单
	 *
	 */
	public List<TMenu> getAllMenuItemList();
	
	public List<String> getMenuByRoleId(String roleIds);
	
	public boolean isUsedByRole(String menuId);
	public List<TMenu> getMenuList02(String string, String string2);
}

