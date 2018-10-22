package net.huaat.system.service.generator;

import java.util.List;

import net.huaat.common.util.database.Column;
import net.huaat.common.util.query.QueryParam;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-11-5 上午11:39:37
 * @version V1.0
 */
public interface CoderService {
	/**
	 * 通过表名获取表的字段信息<br>
	 * POJO方式展示字段名
	 * @param tableName
	 * @return
	 */
	public List<Column> getColumnsInfor(String tableName);
	
	public void generateJavaCode(String projectName, String prefixPackageName,
			String modelName, String prefixClassName, String pojoName);
	
	public void generateViewCode(String projectName, String modelName,
			String prefixClassName, String pojoName, List<Column> gridColumns,
			List<QueryParam> girdQueryParams);

}
