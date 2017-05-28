package net.huaat.common.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.huaat.common.service.BaseService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description: 提供控制器对POJO的基础CRUD支持
 * @author zhiqiang yang
 * @date 2012-10-8 下午2:43:46
 * @version V1.0
 */
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());
	
	public Map<String, Object> map = new HashMap<String, Object>();

	@Resource
	BaseService baseService;

	public boolean add(Object entity) {
		if ( log.isDebugEnabled()) {
			 log.debug("baseContoller saving entity:" + entity.getClass().getName());
		}
		 return baseService.add(entity);
	}

	public void update(Object entity) {
		if ( log.isDebugEnabled()) {
			 log.debug("baseContoller saving entity:" + entity.getClass().getName());
		}
		 baseService.update(entity);
	}

	public boolean saveOrUpdate(Object entity) {
		 return baseService.saveOrUpdate(entity);
	}

	public void delete(Object entity) {
		 baseService.delete(entity);
	}

	/*public List<Object> findByCriteria(DetachedCriteria detachedCriteria) {
		return  baseService.findByCriteria(detachedCriteria);
	}*/

	public Object getEntityById(Class<?> entityClass, int entityId) { 
		return baseService.getEntityById(entityClass, entityId);
	}
	
	public Object getEntityById(Class<?> entityClass, String entityId) { 
		return baseService.getEntityById(entityClass, entityId);
	}
	
	public boolean deleteEntityById(Class<?> entityClass, int entityId) { 
		
		return baseService.delete(getEntityById(entityClass, entityId));
	}
	public boolean deleteEntityById(Class<?> entityClass, String entityId) { 
		
		return baseService.delete(getEntityById(entityClass, entityId));
	}
	
	
	public void jumpError(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/jsp/error.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			log.error("jumpError error:"+e.getMessage());
		}
	}

}
