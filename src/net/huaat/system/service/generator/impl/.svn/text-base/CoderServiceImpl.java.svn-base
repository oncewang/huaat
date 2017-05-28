package net.huaat.system.service.generator.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.huaat.common.util.FreemarkerUtil;
import net.huaat.common.util.JDBCUtils;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.database.Column;
import net.huaat.common.util.database.DBInforProider;
import net.huaat.common.util.file.FileCreate;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.util.query.QueryParamUtil;
import net.huaat.system.constant.Project;
import net.huaat.system.service.generator.CoderService;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-11-5 下午1:01:30
 * @version V1.0  
 */
@Service("coderService")
public class CoderServiceImpl implements CoderService {
	
	public static final String templateDir = Project.PROJECT_ROOT_PATH+"WebContent/resources/ftl/code";

	@Override
	public List<Column> getColumnsInfor(String tableName) {
		List<Column> list = null;
		try {
			Connection connection = JDBCUtils.getConnection();
			list = new DBInforProider().getColumnsInfor(connection, tableName,"view");
			JDBCUtils.closeAll(connection, null,null);
			for(Column column:list){
				column.setColumnName(DBInforProider.dbColumnNameToJavaName(column.getColumnName()));
				column.setDataType(QueryParamUtil.dataTypeToQueryType(column.getDataType())); 
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void generateJavaCode(String projectName, String prefixPackageName,
			String modelName, String prefixClassName, String pojoName){
		try {
			String webPath = "src/"+prefixPackageName.replaceAll("\\.", "/")+"/web/"+modelName;  
			String servicePath = "src/"+prefixPackageName.replaceAll("\\.", "/")+"/service/"+modelName;
			String serviceImplPath = servicePath+"/impl";
			
			
			FileCreate.makeDirFile(Project.PROJECT_ROOT_PATH,webPath);
			//FileCreate.makeDirFile(servicePath);
			//FileCreate.makeDirFile(serviceImplPath);  
			//create the java code of controller  
			Map map = new HashMap();
			map.put("prefixPackageName", prefixPackageName);
			map.put("pojoName", pojoName);
			map.put("modelName", modelName);
			map.put("prefixClassName", prefixClassName);
			map.put("projectName", projectName);//一级模块名称
			map.put("dateTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			
			FreemarkerUtil.process(map, templateDir, "controller.ftl", Project.PROJECT_ROOT_PATH+webPath, StringUtil.upperCaseFirstCharacter(prefixClassName)+"Controller.java"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void generateViewCode(String projectName, String modelName,
			String prefixClassName, String pojoName, List<Column> gridColumns,
			List<QueryParam> girdQueryParams) {
		String viewsPath = projectName+"/"+modelName;
		String rootPath = Project.PROJECT_ROOT_PATH + "WebContent/WEB-INF/views/";
		FileCreate.makeDirFile(rootPath, viewsPath);
		try {
			Map viewListMap = new HashMap();
			viewListMap.put("pojoName", pojoName);
			viewListMap.put("modelName", modelName);
			viewListMap.put("prefixClassName", prefixClassName);
			
			viewListMap.put("gridColumns", gridColumns);
			viewListMap.put("girdQueryParams", girdQueryParams);
			
			FreemarkerUtil.process(viewListMap, templateDir, "list.ftl",rootPath+viewsPath,prefixClassName + "List.jsp");
			FreemarkerUtil.process(viewListMap, templateDir, "detail.ftl",rootPath+viewsPath,prefixClassName + "Detail.jsp");
			//FreemarkerUtil.process(viewListMap, templateDir, "detail.ftl",
			//		Project.PROJECT_ROOT_PATH + "WebContent/WEB-INF/views/"+viewsPath,prefixClassName + "detail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}

