/**
 * 
 */
package net.huaat.estate.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import net.huaat.estate.dao.EstateDao;
import net.huaat.estate.service.EstateService;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-4 下午2:15:28
 * @version V1.0  
 */
@Service("estateService")
public class EstateServiceImpl implements EstateService {
	@Resource
	private EstateDao estateDao;

	/* (non-Javadoc)
	 * @see net.huaat.estate.service.EstateService#callAssessPro(java.lang.String)
	 */
	@Override
	public String callAssessPro(String shengfen,String dishi,String quxian,String address,String community) {
		// TODO Auto-generated method stub
		return estateDao.callAssessPro(shengfen,dishi,quxian,address,community);
	}

	/* (non-Javadoc)
	 * @see net.huaat.estate.service.EstateService#callAssessPinggu(java.lang.String, int, java.lang.Object)
	 */
	@Override
	public int callAssessPinggu(String batchId) {
		// TODO Auto-generated method stub
		return estateDao.callAssessPinggu(batchId);
	}

	/* (non-Javadoc)
	 * @see net.huaat.estate.service.EstateService#callPricenear(java.lang.String)
	 */
	@Override
	public String callPricenear(String string) {
		return estateDao.callPricenear(string);
	} 
	
	
}
