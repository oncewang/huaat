package net.huaat.system.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.huaat.system.dao.SelectorDao;
import net.huaat.system.service.base.SelectorService;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2013-7-5 下午1:12:14
 * @version V1.0  
 */
@Service("selectorService")
public class SelectorServiceImpl implements SelectorService {
	@Resource
	private SelectorDao selectorDao;

	public List<?> getListByParentId(String pojoName, String parentId,String pFieldName) {
		// TODO Auto-generated method stub
		return selectorDao.getListByParentId(pojoName, parentId,pFieldName); 
	}


}

