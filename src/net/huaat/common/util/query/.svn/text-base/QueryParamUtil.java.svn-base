package net.huaat.common.util.query;

import java.util.List;

import net.huaat.common.util.StringUtil;

/**  
 * @Description: 查询对象辅助类，完成查询条件转换成DQL片段
 * @author zhiqiang yang 
 * @date 2012-10-18 下午3:54:17
 * @version V1.0  
 */
public class QueryParamUtil {
	/**
	 * 根据查询条件，得到HQL代码片段
	 * @param queryParams
	 * @return
	 */
	public static String parseToHQL(List<QueryParam> queryParams) {
		if (queryParams==null) {
			return "";
		}
		StringBuffer hql = new StringBuffer();
		for(QueryParam param:queryParams){
			if (StringUtil.isEmpty(param.getValue())) {
				continue;
			}
			hql.append(" and a.").append(param.getId());
			
			switch (Integer.valueOf(param.getRelation())) { 
			case 1:
				 hql.append("=");
				break;
			case 2:
				hql.append(" like ");
				break;
			case 3:
				 hql.append(">=");
				break;
			case 4:
				 hql.append("<=");
				break;
			default:
				break;
			}
			
			hql.append("");
			//append the value
			if (param.getType().equals("1")) {
				if (param.getRelation().equals("2")) {
					hql.append(" '%").append(param.getValue()).append("%'");
				}else {
					hql.append("'").append(param.getValue()).append("'"); 
				}
			}
			if (param.getType().equals("2")) {
				 hql.append(param.getValue());
			}
			if (param.getType().equals("3")) {
				hql.append(" to_date('"+param.getValue()+"','yyyy-MM-dd')");
				//hql.append("'").append(param.getValue()).append("'"); 
			}
			
			if (param.getType().equals("4")) {
				hql.append(" to_date('"+param.getValue()+"','yyyy-MM')");
				//hql.append("'").append(param.getValue()).append("'"); 
			}
		}
		return hql.toString();
	}
	/**
	 * 将DBInforProider.getJavaDataType()中取得的java字段类型转换成查询的类型QueryParam.type
	 * @param type
	 * @return
	 */
	public static String dataTypeToQueryType(String type) {
		if ("String".equals(type)) {
			return "1";
		}else if ("Integer".equals(type)) { 
			return "2";
		}else if ("Date".equals(type)) {
			return "3";
		}
		 return "";
	}
}

