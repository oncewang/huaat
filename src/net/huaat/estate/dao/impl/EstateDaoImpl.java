/**
 * 
 */
package net.huaat.estate.dao.impl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import net.huaat.estate.dao.EstateDao;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-4 下午2:16:23
 * @version V1.0  
 */
@Repository("estateDao")
public class EstateDaoImpl   extends HibernateDaoSupport implements EstateDao {
	@Override
	public String callAssessPro(String shengfen,String dishi,String quxian,String address,String community){
		String flag="0";
		
		 try {
			 CallableStatement statement = getSession().connection().prepareCall(
		                "{CALL sp_estate_price(?,?,?,?,?,?)}");
		     statement.setString(1,shengfen);
		     statement.setString(2,dishi);
		     statement.setString(3,quxian);
		     statement.setString(4,address);
		     statement.setString(5,community);
		     statement.registerOutParameter(6, java.sql.Types.VARCHAR);
		     statement.execute();
		      flag =  statement.getString(6);
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return flag;	
	}

	/* (non-Javadoc)
	 * @see net.huaat.estate.dao.EstateDao#callAssessPinggu(java.lang.String, int, java.lang.String)
	 */
	@Override
	public int callAssessPinggu(String batchId)  {
		int price=0;
		String flag="0";
		
		 try {
			 CallableStatement statement = getSession().connection().prepareCall(
		                "{CALL SP_ESTATE_PRICE_DETAIL(?,?)}");
		     statement.setString(1,batchId);
		     statement.registerOutParameter(2, java.sql.Types.VARCHAR);
		     statement.execute();
		     flag=statement.getString(2);
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return price;
	}

	/* (non-Javadoc)
	 * @see net.huaat.estate.dao.EstateDao#callPricenear(java.lang.String)
	 */
	@Override
	public String callPricenear(String string) {
		// TODO Auto-generated method stub
		String flag="0";
		
		 try {
			 CallableStatement statement = getSession().connection().prepareCall(
		                "{CALL SP_ESTATE_PRICE_NEAR(?,?)}");
		     statement.setString(1,string);
		     statement.registerOutParameter(2, java.sql.Types.VARCHAR);
		     statement.execute();
		     flag=statement.getString(2);
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return flag;
	}
	
}
