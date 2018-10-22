/**
 * 
 */
package net.huaat.estate.service;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-4 下午2:15:02
 * @version V1.0  
 */
public interface EstateService {
	public String callAssessPro(String shengfen,String dishi,String quxian,String address,String community);

	/**
	 * @param id
	 * @param floor
	 * @param attribute
	 * @return
	 */
	public int callAssessPinggu(String batchId);

	/**
	 * @param string
	 */
	public String callPricenear(String string);
}
