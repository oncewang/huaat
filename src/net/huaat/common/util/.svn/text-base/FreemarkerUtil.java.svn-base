package net.huaat.common.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-9-6 下午05:14:41
 * @version V1.0  
 */
public class FreemarkerUtil {
	private static Log log = LogFactory.getLog(FreemarkerUtil.class);
	private final static String CONTENT_ENCODING ="UTF-8";
	/**
	 * 执行模板生成目标文件
	 * @param rootMap 模板文件所需数据
	 * @param templateDir 模板文件所在目录
	 * @param templateName 模板文件名称
	 * @param targetDir 目标文件所在目录
	 * @param targetName 目标文件名称
	 * @throws Exception
	 */
	public static void process(Object rootMap,String templateDir,String templateName,String targetDir,String targetName){
		log.info("start process template...:"+templateDir+" "+templateName);
		try {
			long start=System.currentTimeMillis();
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			Template template = cfg.getTemplate(templateName,CONTENT_ENCODING);
			
			Writer out = new OutputStreamWriter(new FileOutputStream(targetDir+"/"+targetName),"UTF-8");  //输出流  
			
			template.process(rootMap, out);   
			out.flush();
			out.close();
			log.info(targetName+" process success,time(s):"+(System.currentTimeMillis()-start)/1000.000); 
			
		} catch (Exception e) {
			throw new RuntimeException("freemarker proceess ["+targetName+"] error:", e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String rootPath = "";//StringUtil.getRootPath()+"/web/WebContent/resources/ftl/";
		log.info(rootPath);
		Map map = new HashMap();
		map.put("className", "Test");
		map.put("packageName","com1.test");
		
		map.put("commFields", null);
		map.put("funFields", null);
		process(map, rootPath, "javaModel.ftl", "d:","Test.java");
		
		String string="aaa/resources/jar/jarClass";
		log.info(string.substring(0, string.lastIndexOf("resources"))+"WEB-INF/lib/");
	}
}

