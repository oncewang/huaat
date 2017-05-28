package net.huaat.system.web.sys;

import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.huaat.system.pojo.TMenu;
import net.huaat.system.service.sys.MenuService;

/**  
 * @Description: 
 * @author  generator
 * @date 2012-11-17 12:50:48
 * @version V1.0  
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController{

	@Resource
	BaseGridService baseGridService;
	
	@Resource
	MenuService menuService;
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String init(HttpServletRequest request) {
		request.setAttribute("menuItem",menuService.getParentMenu(""));
		return "system/menu/menuList";
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
		
		List<TMenu> list = baseGridService.getList("TMenu", pagination,querList);

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
			TMenu obj = (TMenu)getEntityById(TMenu.class,id);
			request.setAttribute("o", obj);
		}
		request.setAttribute("menuItem",menuService.getParentMenu(id));
		request.setAttribute("tabsList",menuService.getMenuList02("","T"));
		return "system/menu/menuDetail";
	}
	/**
	 * 保存或者新增
	 * @param pojo
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public Map save(TMenu obj,BindingResult errorResult,HttpServletRequest request) {
		Map<String,String> msg = new HashMap<String, String>();
		//System.out.println("++++++++++++++++++++++++++++++"+obj.getParentId());
		obj.setUpdateTime(new Date());
		if (StringUtil.isEmpty(obj.getId())) {
			obj.setId(UUID.randomUUID().toString());
			add(obj);
			msg.put("success", "新增成功");
		}else {
			update(obj);
			msg.put("success", "修改成功");
		}
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
		/** 删除之前先判断当前菜单有没有被使用**/
		if(menuService.isUsedByRole(id)){
			msg.put("error", "该菜单已经被使用，无法删除");
		}else{
			if (deleteEntityById(TMenu.class, id)) {
				msg.put("success", "删除成功");
			}else {
				msg.put("error", "删除失败");
			}
		}
		return  msg; 
	}	
}

