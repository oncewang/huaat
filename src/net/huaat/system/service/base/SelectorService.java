package net.huaat.system.service.base;

import java.util.List;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2013-7-5 下午1:11:32
 * @version V1.0  
 */
public interface SelectorService { 

	public List<?> getListByParentId(String pojoName, String parentId,String pFieldName); 

}

