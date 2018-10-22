package net.huaat.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.huaat.common.dao.BaseDao;
import net.huaat.common.service.BaseService;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-26 下午1:53:21
 * @version V1.0  
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	@Resource
	private BaseDao baseDao;
	@Override
	public boolean add(Object paramObject) {
		// TODO Auto-generated method stub
		return baseDao.add(paramObject); 
	}

	@Override
	public boolean update(Object paramObject) {
		// TODO Auto-generated method stub
		return baseDao.update(paramObject);
	}

	@Override
	public boolean saveOrUpdate(Object paramObject) {
		// TODO Auto-generated method stub
		return baseDao.saveOrUpdate(paramObject); 
	}

	@Override
	public Object getEntityById(Class<?> paramClass, int id) {
		// TODO Auto-generated method stub
		return baseDao.getEntityById(paramClass, id); 
	}

	@Override
	public boolean delete(Object paramObject) {
		// TODO Auto-generated method stub
		return baseDao.delete(paramObject); 
	}

	@Override
	public Object getEntityByProperty(Class<?> entity, String propertyName,
			String propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEntityById(Class<?> paramClass, String id) {
		// TODO Auto-generated method stub
		return baseDao.getEntityById(paramClass, id); 
	}
	
	@Override
	public List<Object[]> getListBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.getListBySql(sql);
	}

	/* (non-Javadoc)
	 * @see net.huaat.common.service.BaseService#updateBySql(java.lang.String)
	 */
	@Override
	public boolean updateBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.updateBySql(sql);
	}



}

