package net.huaat.common.service;

import java.math.BigDecimal;
import java.util.List;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-10-10 下午2:11:23
 * @version V1.0  
 */
public interface BaseGridService {
	/**
	 * 根据对象名称分页查询记录
	 * @param pojoName
	 * @param pagination 分页
	 * @param queryParams 查询条件列表
	 * @return
	 */
	public List getList(String pojoName,Pagination pagination,List<QueryParam> queryParams); 
	/**
	 * 根据对象名称分页查询记录
	 * @param pojoName
	 * @param pagination 分页
	 * @param queryParams 查询条件列表
	 * @param orderStr 排序hql语句
	 * @return
	 */
	public List getListByOrderString(String pojoName, Pagination pagination,List<QueryParam> queryParams,String orderStr);
	/**
	 * Query POJO by pagination,queryParams and order by the given column ASC
	 */
	public <T>List<T> getListByColumnAsc(String pojoName,Pagination pagination,List<QueryParam> queryParams,String columnName);
	/**
	 * Query POJO by pagination,queryParams and order by the given column DESC
	 */
	public <T>List<T> getListByColumnDesc(String pojoName,Pagination pagination,List<QueryParam> queryParams,String columnName);
	public List getList(String pojoName); 
	/**
	 * Query POJO and order by the given column ASC
	 */
	public <T>List<T> getListByColumnAsc(String pojoName,String columnName); 
	/**
	 * Query POJO and order by the given column DESC
	 */
	public <T>List<T> getListByColumnDesc(String pojoName,String columnName);
	public List<Object[]> getListByColAndQueAndTab(Pagination pagination,
			String columns, List<QueryParam> querList, String table,String order);
	
	public List<Object[]>  getListBySql(String sql);
	
	public List<Object[]>  getListByPagAndSql(Pagination pagination,String sql);
	
	public BigDecimal  getUniqueSql(String sql);
	/**
	 * @param sql
	 * @return
	 */
	public List<Object> getListBySql02(String sql);
	

}

