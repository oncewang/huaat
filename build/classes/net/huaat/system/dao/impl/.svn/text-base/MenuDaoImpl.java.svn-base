package net.huaat.system.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import net.huaat.common.util.StringUtil;
import net.huaat.system.dao.MenuDao;
import net.huaat.system.pojo.TMenu;
import net.huaat.system.pojo.TRoleMenu;

/**  
 * @Description: TODO
 * @author hailin chen  
 * @date 2012-11-17 下午5:08:18
 * @version V1.0  
 */
@Repository("menuDao")
public class MenuDaoImpl extends HibernateDaoSupport implements MenuDao {

	public List<TMenu> getMenuList(String id){
		StringBuffer filterStr = new StringBuffer();
		if (!StringUtil.isEmpty(id)) { 
			filterStr.append(" and id!='").append(id).append("'");
		}
		List<TMenu> list = getHibernateTemplate().find("from TMenu where isLeaf='N' "+filterStr+" order by orderRow");
		return list;
	}
	
	@Override
	public List<TMenu> getMenuList02(String id, String isLeaf) {
		StringBuffer filterStr = new StringBuffer();
		if (!StringUtil.isEmpty(id)) { 
			filterStr.append(" and id!='").append(id).append("'");
		}
		List<TMenu> list = getHibernateTemplate().find("from TMenu where isLeaf='"+isLeaf+"' "+filterStr+" order by orderRow");
		return list;
	}
	
	public List<TMenu> getMenuItemList(String menuId){
		List<TMenu> list=getHibernateTemplate().find("from TMenu where parentId='"+StringUtil.removeAllSpaces(menuId)+"' order by orderRow");
		return list;
	}
	
	public List<TMenu> getAllMenuItemList(){
		List<TMenu> list=getHibernateTemplate().find("from TMenu where isLeaf='Y' order by  parentId,orderRow");
		return list;
	}
	
	public List<TRoleMenu> getMenuByRoleId(String condition,String id){
		List<TRoleMenu> list=getHibernateTemplate().find("from TRoleMenu where "+condition+"='"+StringUtil.removeAllSpaces(id)+"'");
		return list;
	}


	
}

