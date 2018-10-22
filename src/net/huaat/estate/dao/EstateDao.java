/**
 * 
 */
package net.huaat.estate.dao;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-4 下午2:15:52
 * @version V1.0  
 */
public interface EstateDao {
	public String callAssessPro(String shengfen,String dishi,String quxian,String address,String community);

	/**
	 * @param id
	 * @param floor
	 * @param userId
	 * @return
	 */
	public int callAssessPinggu(String batchId) ;

	/**
	 * @param string
	 * @return
	 */
	public String callPricenear(String string);
}
