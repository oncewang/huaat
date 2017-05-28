package net.huaat.system.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.util.query.QueryParamUtil;
import net.huaat.system.dao.QualityDao;
@Repository("qualityDao")
public class QualityDaoImpl  extends HibernateDaoSupport  implements QualityDao {

	@Override
	public List<Object[]> getAllProgress() {
		Query query = getSession().createSQLQuery(
				"select progress_name from t_progress_list  order by progress_name");
		return query.list();
	}

	@Override
	public List<Object[]> getIncomeList(Pagination pagination,String columns,
			List<QueryParam> querList, String table) {
		String filterStr = QueryParamUtil.parseToHQL(querList);
		String sql="Select "+columns+" From "+table+"  a where 1=1  "+filterStr+"  Order By a.dateid,a.progress_name ";
		System.out.println("sql+++++++++:"+sql);
		Query queryAll = getSession().createSQLQuery("select count(*) from ("+sql+")");
		
		pagination.setAllCount(((Number) queryAll.uniqueResult()).intValue());
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(pagination.getHqlFirstResult());
	    query.setMaxResults(pagination.getPageSize());
		
		return query.list();
	}

	@Override
	public List<Object[]> getListObject(List<QueryParam> querList, String table) {
		String filterStr = QueryParamUtil.parseToHQL(querList);
		Query query = getSession().createSQLQuery(
				"select qa0001m,qa0003m from "+table+" a where 1=1   "+filterStr+"  order by qa0001m");
		return query.list();
	}

}
