package net.huaat.system.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import net.huaat.common.util.StringUtil;
import net.huaat.system.dao.RoleDao;
import net.huaat.system.pojo.TRoleMenu;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-10-8 下午3:52:18
 * @version V1.0  
 */
@Repository("roleDao")
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	public void deleteByRoleId(String roleId){
		getSession().createQuery("delete  from TRoleMenu where roleId='"+StringUtil.removeAllSpaces(roleId)+"'").executeUpdate();
	}
	
	
	public List<TRoleMenu> getList(String roleId){
		return getHibernateTemplate().find("from TRoleMenu where roleId='"+StringUtil.removeAllSpaces(roleId)+"'");
	}
	
	public List getUserList(String roleId){
		return getHibernateTemplate().find("from TUser where roleIds like '%"+StringUtil.removeAllSpaces(roleId)+"%'");
	}
}

