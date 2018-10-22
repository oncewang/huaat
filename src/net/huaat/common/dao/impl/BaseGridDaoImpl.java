package net.huaat.common.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import net.huaat.common.dao.BaseGridDao;
import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.util.query.QueryParamUtil;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-10-10 下午2:16:43
 * @version V1.0  
 */
@Repository("baseGridDao")
public class BaseGridDaoImpl extends HibernateDaoSupport implements BaseGridDao {

	@Override
	public List<?> getList(String pojoName, Pagination pagination,List<QueryParam> queryParams) {
		String filterStr = QueryParamUtil.parseToHQL(queryParams);
		
		Query queryAll = getSession().createQuery("select count(*) from "+pojoName+" a where 1=1 "+filterStr);

		pagination.setAllCount(((Long) queryAll.uniqueResult()).intValue());

		Query query = getSession().createQuery("from "+pojoName+" a where 1=1 "+filterStr);// + " order by name desc");

		query.setFirstResult(pagination.getHqlFirstResult());
		query.setMaxResults(pagination.getPageSize());

		return query.list();
	}
	
	@Override
	public List<?> getListByOrderString(String pojoName, Pagination pagination,List<QueryParam> queryParams, String orderStr) {
		String filterStr = QueryParamUtil.parseToHQL(queryParams);
		
		Query queryAll = getSession().createQuery("select count(*) from "+pojoName+" a where 1=1 "+filterStr);

		pagination.setAllCount(((Long) queryAll.uniqueResult()).intValue());

		Query query = getSession().createQuery("from "+pojoName+" a where 1=1 "+filterStr+"  "+orderStr);// + " order by name desc");

		query.setFirstResult(pagination.getHqlFirstResult());
		query.setMaxResults(pagination.getPageSize());

		return query.list();
	}
	@Override
	public <T> List<T> getListByColumnAsc(String pojoName,
			Pagination pagination, List<QueryParam> queryParams,
			String columnName) {
		return getListAscOrDesc(pojoName, pagination, queryParams, columnName, "asc");
	}

	@Override
	public <T> List<T> getListByColumnDesc(String pojoName,
			Pagination pagination, List<QueryParam> queryParams,
			String columnName) {
		return getListAscOrDesc(pojoName, pagination, queryParams, columnName, "desc");
	}

	@Override
	public List getList(String pojoName) {
		return getHibernateTemplate().find("from "+pojoName); 
	}
	@Override
	public <T> List<T> getListByColumnAsc(String pojoName, String columnName) {
		return getHibernateTemplate().find("from "+pojoName+" order by "+columnName+" asc");  
	}

	@Override
	public <T> List<T> getListByColumnDesc(String pojoName, String columnName) {
		return getHibernateTemplate().find("from "+pojoName+" order by "+columnName+" desc");  
	}
	
	
	private List getListAscOrDesc(String pojoName,
			Pagination pagination, List<QueryParam> queryParams,
			String columnName,String type){
		String filterStr = QueryParamUtil.parseToHQL(queryParams);
		
		Query queryAll = getSession().createQuery("select count(*) from "+pojoName+" a where 1=1 "+filterStr);

		pagination.setAllCount(((Long) queryAll.uniqueResult()).intValue());

		Query query = getSession().createQuery("from "+pojoName+" a where 1=1 "+filterStr+ " order by "+columnName+" "+type);

		query.setFirstResult(pagination.getHqlFirstResult());
		query.setMaxResults(pagination.getPageSize());

		return query.list();
	}

	@Override
	public List<Object[]> getListByColAndQueAndTab(Pagination pagination,
			String columns, List<QueryParam> querList, String table,String order) {
		String filterStr = QueryParamUtil.parseToHQL(querList);
		String sql="Select "+columns+" From "+table+"  a where 1=1  "+filterStr+order;
		Query queryAll = getSession().createSQLQuery("select count(*) from ("+sql+")");
		
		pagination.setAllCount(((Number) queryAll.uniqueResult()).intValue());
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(pagination.getHqlFirstResult());
	    query.setMaxResults(pagination.getPageSize());
		
		return query.list();
	}

	/* (non-Javadoc)
	 * @see net.huaat.common.dao.BaseGridDao#getListByPagAndSql(net.huaat.common.util.pagination.Pagination, java.lang.String)
	 */
	@Override
	public List<Object[]> getListByPagAndSql(Pagination pagination, String sql) {
		// TODO Auto-generated method stub
	
		Query queryAll = getSession().createSQLQuery("select count(*) from ("+sql+") as total ");
		
		pagination.setAllCount(((Number) queryAll.uniqueResult()).intValue());
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(pagination.getHqlFirstResult());
	    query.setMaxResults(pagination.getPageSize());
		
		return query.list();
	}

	/* (non-Javadoc)
	 * @see net.huaat.common.dao.BaseGridDao#getListBySql(java.lang.String)
	 */
	@Override
	public List<Object[]> getListBySql(String sql) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery(sql);
		
		return query.list();
	}

	/* (non-Javadoc)
	 * @see net.huaat.common.dao.BaseGridDao#getUniqueSql(java.lang.String)
	 */
	@Override
	public BigDecimal getUniqueSql(String sql) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery(sql);
		return (BigDecimal) query.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see net.huaat.common.dao.BaseGridDao#getListBySql02(java.lang.String)
	 */
	@Override
	public List<Object> getListBySql02(String sql) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery(sql);
		
		return query.list();
	}


}

