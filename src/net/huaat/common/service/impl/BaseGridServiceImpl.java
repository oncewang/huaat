package net.huaat.common.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.huaat.common.dao.BaseGridDao;
import net.huaat.common.service.BaseGridService;
import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-10-10 下午2:15:09
 * @version V1.0  
 */
@Service("BaseGridService")
public class BaseGridServiceImpl implements BaseGridService {

	@Resource
	private BaseGridDao baseGridDao;
	@Override
	public List getList(String pojoName, Pagination pagination,List<QueryParam> queryParams) {
		return baseGridDao.getList(pojoName, pagination,queryParams);
	}
	@Override
	public List getListByOrderString(String pojoName, Pagination pagination,List<QueryParam> queryParams,String orderStr) {
		return baseGridDao.getListByOrderString(pojoName, pagination,queryParams,orderStr);
	}
	@Override
	public List getList(String pojoName) {
		// TODO Auto-generated method stub
		return baseGridDao.getList(pojoName); 
	}
	@Override
	public <T> List<T> getListByColumnAsc(String pojoName,
			Pagination pagination, List<QueryParam> queryParams,
			String columnName) {
		// TODO Auto-generated method stub
		return baseGridDao.getListByColumnAsc(pojoName, pagination, queryParams, columnName);
	}
	@Override
	public <T> List<T> getListByColumnDesc(String pojoName,
			Pagination pagination, List<QueryParam> queryParams,
			String columnName) {
		// TODO Auto-generated method stub
		return baseGridDao.getListByColumnDesc(pojoName, pagination, queryParams, columnName); 
	}
	@Override
	public <T> List<T> getListByColumnAsc(String pojoName, String columnName) {
		// TODO Auto-generated method stub
		return baseGridDao.getListByColumnAsc(pojoName, columnName);
	}
	@Override
	public <T> List<T> getListByColumnDesc(String pojoName, String columnName) {
		// TODO Auto-generated method stub
		return baseGridDao.getListByColumnDesc(pojoName, columnName); 
	}
	@Override
	public List<Object[]> getListByColAndQueAndTab(Pagination pagination,
			String columns, List<QueryParam> querList, String table,String order) {
		// TODO Auto-generated method stub
		return baseGridDao.getListByColAndQueAndTab(pagination,columns,querList,table,order);
	}
	/* (non-Javadoc)
	 * @see net.huaat.common.service.BaseGridService#getListByPagAndSql(net.huaat.common.util.pagination.Pagination, java.lang.String)
	 */
	@Override
	public List<Object[]> getListByPagAndSql(Pagination pagination, String sql) {
		// TODO Auto-generated method stub
		return baseGridDao.getListByPagAndSql(pagination,sql);
	}
	/* (non-Javadoc)
	 * @see net.huaat.common.service.BaseGridService#getListBySql()
	 */
	@Override
	public List<Object[]> getListBySql(String sql) {
		// TODO Auto-generated method stub
		return baseGridDao.getListBySql(sql);
	}
	/* (non-Javadoc)
	 * @see net.huaat.common.service.BaseGridService#getUniqueSql(java.lang.String)
	 */
	@Override
	public BigDecimal getUniqueSql(String sql) {
		// TODO Auto-generated method stub
		return baseGridDao.getUniqueSql(sql);
	}
	/* (non-Javadoc)
	 * @see net.huaat.common.service.BaseGridService#getListBySql02(java.lang.String)
	 */
	@Override
	public List<Object> getListBySql02(String sql) {
		// TODO Auto-generated method stub
		return baseGridDao.getListBySql02(sql);
	}

}

