package net.huaat.system.service.sys.impl;

import java.util.*;

import javax.annotation.Resource;

import net.huaat.common.util.StringUtil;
import net.huaat.system.dao.MenuDao;
import net.huaat.system.pojo.TMenu;
import net.huaat.system.pojo.TRoleMenu;
import net.huaat.system.service.sys.MenuService;

import org.springframework.stereotype.Service;


/**  
 * @Description: TODO
 * @author hailin chen 
 * @date 2012-11-17 下午4:20:46
 * @version V1.0  
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuDao menuDao;
	
	public String getUserMenuHTML(List<String> list,String path) {
		StringBuffer menu=new StringBuffer();
		List<TMenu> allParentList=menuDao.getMenuList("");
		for(int i=0;allParentList!=null&&i<allParentList.size();i++){
			TMenu t1=allParentList.get(i);
			if(list.contains(t1.getId())){
				menu.append("<div title='").append(t1.getName()).append("'").append("style='padding:10px;overflow:auto;'>");
				List<TMenu> item=menuDao.getMenuItemList(t1.getId());
				for(int j=0;item!=null&&j<item.size();j++){
					TMenu t2=item.get(j);
					if(list.contains(t2.getId())){
						menu.append("<div class='sys_menu_leaf'>")
							.append("<img src='").append(path).append("/resources/images/leaf.gif'/>")
							.append("<a href='#' onclick=\"addTab('").append(t2.getName()).append("','")
							.append(t2.getId()).append("','");
							if(!t2.getOpenType().equals("3")){
								menu.append(path);
							}
							menu.append(t2.getUrl())
							.append("','").append(t2.getOpenType()).append("')\">").append(t2.getName()).append("</a></div>");	
					}	
				}	 
				menu.append("</div>");
			}
		}
		return menu.toString();
	}
	
	public String getUserTabsHTML(List<String> list,String path) {
		StringBuffer menuTabs=new StringBuffer();
		List<TMenu> allTabsList=menuDao.getMenuList02("","T");
		for(int i=0;allTabsList!=null&&i<allTabsList.size();i++){
			TMenu tab=allTabsList.get(i);
			List<TMenu> allParentList=menuDao.getMenuItemList(tab.getId());
			StringBuffer menu=new StringBuffer();
			menu.append("<div id='").append(tab.getId()).append("'  class='container' style='margin-top:5px;' title=\"<img   style='margin-top:7px;margin-right:5px;height:16px'  src='").append(path).append(tab.getIconUrl()).append("'>").append(tab.getName()).append("\" >");
			//menu.append("<div class='easyui-layout' fit='true' border='false'>");
			//menu.append("<div region='west' border='false' split='true' title=\"<img   style='margin-top:0px;margin-right:5px;height:16px'  src='").append(tab.getIconUrl()).append("'>").append(tab.getName()).append("\" style='width: 200px; padding1: 1px; overflow: hidden;'>");
			menu.append("<div class='easyui-accordion acc_sty_left'    border='false'>");
			boolean notNull=false;
			for(int k=0;allParentList!=null&&k<allParentList.size();k++){
				TMenu t1=allParentList.get(k);
				if(list.contains(t1.getId())){
					notNull=true;
					menu.append("<div title='").append(t1.getName()).append("'").append("   style='padding:10px;overflow:auto;'>");
					List<TMenu> item=menuDao.getMenuItemList(t1.getId());
					for(int j=0;item!=null&&j<item.size();j++){
						TMenu t2=item.get(j);
						if(list.contains(t2.getId())){
							menu.append("<div class='sys_menu_leaf'>")
								.append("<img src='").append(path).append("/resources/images/leaf.gif'/>")
								.append("<a href='#' onclick=\"addTab('").append(t2.getName()).append("','")
								.append(t2.getId()).append("','");
								if(!t2.getOpenType().equals("3")){
									menu.append(path);
								}
								menu.append(t2.getUrl())
								.append("','").append(t2.getOpenType()).append("' ,'").append(tab.getId()).append("tabs')\">").append(t2.getName()).append("</a></div>");	
						}	
					}	 
					menu.append("</div>");
				}
			}
			menu.append("</div><div class='acc_sty_right'  > <div id='").append(tab.getId()).append("tabs' class='easyui-tabs tab-styl'  border='false'></div></div></div>");
			if(notNull){
				menuTabs.append(menu);
			}
		}
		
		return menuTabs.toString();
	}
		
	public List<TMenu> getParentMenu(String id){
		return menuDao.getMenuList(id);
	}
	
	public List<TMenu> getAllMenuItemList(){
		return menuDao.getAllMenuItemList();
	}
	
	public List<String> getMenuByRoleId(String roleIds){
		List<String> list=null;
		if(!StringUtil.isEmpty(roleIds)){
			list=new ArrayList<String>();
			String[] roleId=roleIds.split(",");
			for(int i=0;i<roleId.length;i++){
				List<TRoleMenu> menulist=menuDao.getMenuByRoleId("roleId",roleId[i]);
				for(int j=0;menulist!=null&&j<menulist.size();j++){
					TRoleMenu troleMenu=menulist.get(j);
					if(!list.contains(troleMenu.getMenuId())){
						list.add(troleMenu.getMenuId());
					}
				}
			}
		}
		return list;
	}
	
	public boolean isUsedByRole(String menuId){
		List<TRoleMenu> list=menuDao.getMenuByRoleId("menuId", menuId);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<TMenu> getMenuList02(String string, String string2) {
		// TODO Auto-generated method stub
		return menuDao.getMenuList02("","T");
		
	}
}

