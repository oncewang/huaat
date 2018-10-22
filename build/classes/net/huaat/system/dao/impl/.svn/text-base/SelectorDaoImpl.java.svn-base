package net.huaat.system.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import net.huaat.common.util.StringUtil;
import net.huaat.system.dao.SelectorDao;
/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2013-7-5 下午1:58:59
 * @version V1.0  
 */
@Repository("selectorDao")
public class SelectorDaoImpl extends HibernateDaoSupport implements SelectorDao {

	public List<?> getListByParentId(String pojoName, String parentId,String pFieldName) {
		if (StringUtil.isEmpty(parentId)) {
			parentId="0";
		}
		//String need to do handle;
		String hql = "from "+pojoName+" where "+pFieldName+"="+parentId;
		return getHibernateTemplate().find(hql); 
	}
	

}

