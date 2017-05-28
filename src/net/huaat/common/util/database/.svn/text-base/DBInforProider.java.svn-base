package net.huaat.common.util.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.huaat.common.util.JDBCUtils;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.database.DBInforConstant.DBDialect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**  
 * @Description: TODO
 * @author zhiqiang yang  
 * @date 2012-6-7 下午12:01:12
 * @version V1.0  
 */
public class DBInforProider {
	
	private Log log = LogFactory.getLog(getClass());
	public DBInforProider(DBDialect dbdDialect){
		DBInforConstant.DB_DIALECT = dbdDialect;
	}
	public DBInforProider(){
		
	}
	/**
	 * 根本数据库表名称获取表的字段信息
	 * @param tableName
	 * @return
	 * @throws Exception 
	 */
	public List<Column> getColumnsInfor(Connection connection,String tableName,String tableSchema) throws Exception {
		List<Column> list = new ArrayList<Column>();
		
		String SQLColumns = "";
		 
		switch (DBInforConstant.DB_DIALECT) {
		case Oracle:
			SQLColumns = "SELECT a.COLUMN_NAME,a.DATA_TYPE,b.comments from user_tab_columns a LEFT OUTER JOIN user_col_comments b " +
					"ON a.TABLE_NAME=b.table_name AND a.COLUMN_NAME=b.column_name WHERE lower(a.TABLE_NAME)=lower('"+tableName+"')";
			break;
		case MySQL:
			SQLColumns = "SELECT distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM information_schema.columns WHERE table_name =  '"
		+tableName+"' and table_schema='"+tableSchema+"'";
			break;
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SQLColumns); 
			rs = statement.executeQuery();
			while(rs.next()){
				Column column = new Column();
				column.setColumnName(rs.getString(1).toLowerCase());
				column.setDataType(getJavaDataType(rs.getString(2))); 
				log.info(rs.getString(2)+"===>"+column.getDataType());
				column.setComment(rs.getString(3));
				list.add(column);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		}finally{
			JDBCUtils.closeAll(null, rs,null);
			
		}
		return list;
	}
	/**
	 * Get the data type of java  from the DB type
	 * @param DBDataType
	 * @return
	 */
	public String getJavaDataType(String DBDataType) {
		switch (DBInforConstant.DB_DIALECT) {
		case Oracle:
			if ("VARCHAR2".equalsIgnoreCase(DBDataType)) {
				return "String";
			}
			if ("NUMBER".equalsIgnoreCase(DBDataType)) {
				return "int";
			}
			if ("DATE".equalsIgnoreCase(DBDataType)||"TIMESTAMP".equalsIgnoreCase(DBDataType)) {
				return "java.util.Date";
			}
			break;
		case MySQL:
			if ("char".equalsIgnoreCase(DBDataType)||"varchar".equalsIgnoreCase(DBDataType)||"text".equalsIgnoreCase(DBDataType)) {
				return "String";
			}
			if ("date".equalsIgnoreCase(DBDataType)||"datetime".equalsIgnoreCase(DBDataType)) {
				return "Date";
			}
			if ("int".equalsIgnoreCase(DBDataType)) {
				return "Integer";
			}
			break;
		}
		log.error("Not fond the validity type for "+DBDataType);
		return DBDataType;
	}
	/**
	 * 将数据库中字段名称转换为JAVA格式字段名称，如：去掉 “-”
	 * @param dbName
	 * @return
	 */
	public static String dbColumnNameToJavaName(String dbName) {
		StringBuffer sb = new StringBuffer();
		String [] names = dbName.split("_");
		for(String name:names){
			sb.append(StringUtil.upperCaseFirstCharacter(name));
		}
		return StringUtil.lowerCaseFirstCharacter(sb.toString());
	}
	
	public static void main(String[] args) throws Exception {
		Connection connection = JDBCUtils.getConnection(); 
		new DBInforProider().getColumnsInfor(connection, "t_user","view");
		/*Connection connection = JDBCUtils.getConnection("jdbc_view.properties"); 
		new DBInforProider(DBDialect.Oracle).getColumnsInfor(connection, "td_label");*/
	}
	
}
