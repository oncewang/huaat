package net.huaat.common.dao;

import java.util.List;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-26 下午1:56:42
 * @version V1.0  
 */
public interface BaseDao {
	public  boolean add(Object paramObject);
	
	public  boolean update(Object paramObject);

	public  boolean saveOrUpdate(Object paramObject);

	public  Object getEntityById(Class<?> paramClass, int id);
	
	public  Object getEntityById(Class<?> paramClass, String id);

	public  boolean delete(Object paramObject);

	public Object getEntityByProperty(Class<?> entity, String propertyName,
			String propertyValue);

	/**
	 * @param sql
	 * @return
	 */
	public List<Object[]> getListBySql(String sql);

	/**
	 * @param sql
	 * @return
	 */
	public boolean updateBySql(String sql);
}

