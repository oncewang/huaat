package net.huaat.common.util.generator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.huaat.common.util.FreemarkerUtil;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.file.FileCreate;

/**  
 * @Description: 简单CRUD代码生成器工具类
 * @author zhiqiang yang 
 * @date 2012-10-19 上午9:58:43
 * @version V1.0  
 */
public class CodeGenerator {
	/**
	 * 
	 * @param prefixPackageName 包名前缀 如：net.huaat.hdms 
	 * @throws Exception 
	 */
	/*public  void createJava(String projectName,String prefixPackageName,String modelName,String prefixClassName,String pojoName) throws Exception {
		String webPath = "src/"+prefixPackageName.replaceAll("\\.", "/")+"/web/"+modelName;  
		String servicePath = "src/"+prefixPackageName.replaceAll("\\.", "/")+"/service/"+modelName;
		String serviceImplPath = servicePath+"/impl";
		String viewsPath = projectName+"/"+modelName;
		
		String templateDir = Project.PROJECT_ROOT_PATH+"WebContent/resources/ftl/code";

		FileCreate.makeDirFile(Project.PROJECT_ROOT_PATH,webPath);
		FileCreate.makeDirFile(Project.PROJECT_ROOT_PATH+"WebContent/WEB-INF/views/", viewsPath);
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
		
		//create the java code of views
		Map viewListMap = new HashMap();
		viewListMap.put("pojoName", pojoName);
		viewListMap.put("modelName", modelName);
		viewListMap.put("prefixClassName", prefixClassName);
		FreemarkerUtil.process(viewListMap, templateDir, "list.ftl", Project.PROJECT_ROOT_PATH+"WebContent/WEB-INF/views/", prefixClassName+"List.jsp");
		
		//create the java code of service
		//FreemarkerUtil.process(map, templateDir, "service.ftl", servicePath, prefixClassName+"Service.java"); 
		//FreemarkerUtil.process(map, templateDir, "serviceImpl.ftl", serviceImplPath, prefixClassName+"ServiceImpl.java"); 
	}
	
	public static void createView() { 
		
	}
	public static String getRootPath() {
		String rootPath = "";
		try {
			File file = new File(CodeGenerator.class.getResource("/").getFile());
			rootPath = file.getParent();
			rootPath = java.net.URLDecoder.decode(rootPath, "utf-8");
			rootPath = rootPath.substring(0, rootPath.indexOf(Project.PROJECT_NAME))+Project.PROJECT_NAME; 
			return rootPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootPath;
	}

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		System.out.println(System.getProperty("os.arch")); 
		String path = getRootPath()+"/WebContent/resources/ftl";
		System.out.println(path);
		
		System.out.println( "user.dir ".replaceAll("\\.", "/")); 
	}*/

}

