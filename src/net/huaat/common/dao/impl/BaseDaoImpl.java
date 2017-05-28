package net.huaat.common.dao.impl;

import java.util.List;

import net.huaat.common.dao.BaseDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-10-10 下午4:32:47
 * @version V1.0
 */
@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	Log log = LogFactory.getLog(getClass());
	@Override
	public boolean add(Object paramObject) {
		try {
			getHibernateTemplate().save(paramObject);
		} catch (Exception e) {
			log.error("add object error:"+e.getMessage());
		}
		return true;
	}
	@Override
	public Object getEntityById(Class entity, int entityId) {
		return getHibernateTemplate().get(entity, entityId);
	}
	@Override
	public boolean update(Object entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (Exception e) {
			log.error("update ["+entity+"] object error:"+e.getMessage());
		}
		return true;
	}
	@Override
	public boolean saveOrUpdate(Object entity) {
		if (log.isDebugEnabled()) {
			log.debug("saveOrUpdate ["+entity+"]");
		}
		getHibernateTemplate().saveOrUpdate(entity);
		return true;
	}
	@Override
	public boolean delete(Object entity) {
		try {
			getHibernateTemplate().delete(entity);
			
		} catch (Exception e) {
			log.error("delete entity error:"+e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Object getEntityByProperty(Class<?> entity, String propertyName,
			String propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getEntityById(Class<?> paramClass, String entityId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(paramClass, entityId);
	}
	
	@Override
	public List<Object[]> getListBySql(String sql) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery(sql);
		
		return query.list();
	}
	
	/* (non-Javadoc)
	 * @see net.huaat.common.dao.BaseDao#updateBySql(java.lang.String)
	 */
	@Override
	public boolean updateBySql(String sql) {
		// TODO Auto-generated method stub
		Query query =getSession().createSQLQuery(sql);
		query.executeUpdate();
		
		return true;
	}

}
