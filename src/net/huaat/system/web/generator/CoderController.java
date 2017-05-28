package net.huaat.system.web.generator;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.huaat.common.util.StringUtil;
import net.huaat.common.util.database.Column;
import net.huaat.common.util.database.DBInforProider;
import net.huaat.common.util.query.QueryParam;
import net.huaat.system.service.generator.CoderService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**  
 * @Description: 代码生成器控制
 * @author zhiqiang yang 
 * @date 2012-10-24 下午6:05:51
 * @version V1.0  
 */
@Controller
@RequestMapping("/generator/coder")
public class CoderController {
	
	@Resource
	private CoderService coderService;
	
	@RequestMapping(value="init",method = RequestMethod.GET)
	public String init() {
		return "system/generator/init";
	}
	@RequestMapping(value="dbInfor",method = RequestMethod.GET)
	public String dbInfor(Model model,String tableName) throws Exception {
		if (!StringUtil.isEmpty(tableName)) {
			model.addAttribute("columns",coderService.getColumnsInfor(tableName));
			model.addAttribute("pojoName", StringUtil.upperCaseFirstCharacter(DBInforProider.dbColumnNameToJavaName(tableName)));
		}
		return "system/generator/dbInfor";
	}
	
	@RequestMapping(value="generat",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> generat(HttpServletRequest request) throws Exception {
		String projectName = request.getParameter("projectName").toLowerCase();
		String prefixPackageName = "net.huaat."+projectName;
		String pojoName = request.getParameter("pojoName"); 
		String modelName = request.getParameter("modelName");
		String prefixClassName = request.getParameter("prefixClassName");
		
		//columns
		String columns = request.getParameter("girdColumns");
		String queryParams = request.getParameter("girdQueryParams");
		List<Column> gridColumns =JSONArray.toList(JSONArray.fromObject(columns),new Column(),new JsonConfig());
		List<QueryParam> girdQueryParams =JSONArray.toList(JSONArray.fromObject(queryParams),new QueryParam(),new JsonConfig());
		
		String codeType = request.getParameter("codeType");
		if (codeType.equals("java")) {
			coderService.generateJavaCode(projectName, prefixPackageName, modelName, prefixClassName, pojoName);
			 
		}else if (codeType.equals("jsp")) {
			coderService.generateViewCode(projectName, modelName, prefixClassName, pojoName, gridColumns, girdQueryParams);
		}else{
			coderService.generateJavaCode(projectName, prefixPackageName, modelName, prefixClassName, pojoName);
			coderService.generateViewCode(projectName, modelName, prefixClassName, pojoName, gridColumns, girdQueryParams);
		}
		
		return null;
	}
	
}

