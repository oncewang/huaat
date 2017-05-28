/**
 * 
 */
package net.huaat.areas.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.huaat.common.service.BaseService;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.pagination.Pagination;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-5 下午5:01:17
 * @version V1.0  
 */
@Controller
@RequestMapping("/areas")
public class AreasController {
	@Resource
	BaseService baseService;
	
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(HttpServletRequest request,HttpSession session,String parentid) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		String sql="SELECT id,area_name FROM areas WHERE parent_id="+parentid+" ORDER BY id";
		
		List<Object[]>  list=baseService.getListBySql(sql);
		
		map.put("list", list);
		map.put("success", true);
		
		return map;	
	}
}
