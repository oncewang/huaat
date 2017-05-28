package net.huaat.common.util;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-7-30 下午05:52:36
 * @version V1.0  
 */
public class JDBCUtils {
	
	public static Connection getConnection() throws Exception {
		return getConnection("jdbc.properties");
	}
	/**
	 * 通过配置文件名称取得一个连接，如："jdbc.properties"
	 * @param propertiesName
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String propertiesName) throws Exception {
		InputStream inStream = JDBCUtils.class.getClassLoader().getResourceAsStream(propertiesName);
		Properties properties = new Properties();
		properties.load(inStream);
		Class.forName(properties.getProperty("driverClassName"));
		return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
	}
	
	public static void closeAll(Connection connection,ResultSet rs,PreparedStatement pst) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int executeUpdate(Connection connection,String sqlCode){
		//System.out.println(sqlCode);
		int i=0;
		PreparedStatement pst=null;
		try {
			pst = connection.prepareStatement(sqlCode);
			i=pst.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("execute sqlCode ["+sqlCode+"] error", e);
		}finally{
			JDBCUtils.closeAll(connection, null, pst);
		}
		return i;
	}
	public static List<Object[]> executeQuery(Connection connection,String sqlCode){
		//System.out.println(sqlCode);
		List<Object[]> list = new ArrayList<Object[]>();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst = connection.prepareStatement(sqlCode);
			rs = pst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			Object[] obj=null;
			while(rs.next()){
				obj = new Object[columnCount];
				for(int i=0;i<columnCount;i++){
					obj[i]=rs.getObject(i+1);
				}
				list.add(obj);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("execute sqlCode ["+sqlCode+"] error", e);
		}finally{
			JDBCUtils.closeAll(connection, rs, pst);
		}
		return list;
	}
	public static List<Map<String, String>> executeQueryForMap(Connection connection,String sqlCode){
		//System.out.println(sqlCode);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try { 
			pst = connection.prepareStatement(sqlCode);
			rs = pst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			Map<String, String> map=null;
			while(rs.next()){
				map = new HashMap<String, String>();
				for(int i=0;i<columnCount;i++){
					map.put(metaData.getColumnName(i+1),rs.getObject(i+1)==null?"":rs.getObject(i+1).toString());
				}
				list.add(map);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("execute sqlCode ["+sqlCode+"] error", e);
		}finally{
			JDBCUtils.closeAll(connection, rs, pst);
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from tb_info_meta");
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1)); 
		}
		closeAll(connection,rs,statement);
	}

}

