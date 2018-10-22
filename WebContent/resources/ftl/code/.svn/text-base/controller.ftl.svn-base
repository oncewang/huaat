package ${prefixPackageName}.web.${modelName};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.huaat.common.service.BaseGridService;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.web.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${prefixPackageName}.pojo.${pojoName};

/**  
 * @Description: 
 * @author  generator
 * @date ${dateTime}
 * @version V1.0  
 */
@Controller
@RequestMapping("/${modelName}/${prefixClassName}")
public class ${prefixClassName?cap_first}Controller extends BaseController{

	@Resource
	BaseGridService baseGridService;
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String init() {
		return "${projectName}/${modelName}/${prefixClassName}List";
	}
	/**
	 * 根据条件分页查询
	 * @param queryParams 查询参数,JSON格式
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,String queryParams) {
		
		Pagination pagination = new Pagination();
		pagination.setPageNumber(Integer.valueOf(request.getParameter("page")));
		pagination.setPageSize(Integer.valueOf(request.getParameter("rows")));
		
		List<QueryParam> querList =null;
		
		if (!StringUtil.isEmpty(queryParams)) {
			querList=JSONArray.toList(JSONArray.fromObject(queryParams),new QueryParam(),new JsonConfig()); 
		}
		
		List<${pojoName}> list = baseGridService.getList("${pojoName}", pagination,querList);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", pagination.getAllCount());
		map.put("rows", list);
		return map ; 
	}
	/**
	 * 打开明细
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtil.isEmpty(id)) {
			${pojoName} obj = (${pojoName})getEntityById(${pojoName}.class,id);
			request.setAttribute("o", obj);
		}
		return "${projectName}/${modelName}/${prefixClassName}Detail";
	}
	/**
	 * 保存或者新增
	 * @param pojo
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public Map save(${pojoName} obj,BindingResult errorResult) {
		Map<String,String> msg = new HashMap<String, String>();
		if (StringUtil.isEmpty(obj.getId())) {
			obj.setId(UUID.randomUUID().toString());
			add(obj);
		}else {
			update(obj);
		}
		msg.put("success", "success");
		return  msg; 
	}
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Map delete(@PathVariable("id") String id ) {
		Map<String,String> msg = new HashMap<String, String>();
		if (deleteEntityById(${pojoName}.class, id)) {
			msg.put("success", "删除成功");
		}else {
			msg.put("error", "error");
		}
		return  msg; 
	}
	
}

