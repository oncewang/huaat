package net.huaat.system.web.sys;

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

import net.huaat.system.pojo.TRole;
import net.huaat.system.pojo.TRoleMenu;
import net.huaat.system.service.sys.MenuService;
import net.huaat.system.service.sys.RoleService;

/**  
 * @Description: 
 * @author  generator
 * @date 2012-12-05 02:25:22
 * @version V1.0  
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController{

	@Resource
	BaseGridService baseGridService;
	
	@Resource
	MenuService menuService;
	
	@Resource
	RoleService roleService;
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String init() {
		return "system/role/roleList";
	}
	/**
	 * 根据条件分页查询
	 * @param queryParams 查询参数,JSON格式
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,String queryParams) {
		String paging=request.getParameter("pagination");
		Map<String,Object> map = new HashMap<String, Object>();
		List<TRole> list=null;
		if(!StringUtil.isEmpty(paging)&&"false".equals(paging)){
			list = baseGridService.getList("TRole");
			map.put("rows", list);
		}else{
			Pagination pagination = new Pagination();
			pagination.setPageNumber(Integer.valueOf(request.getParameter("page")));
			pagination.setPageSize(Integer.valueOf(request.getParameter("rows")));
			
			List<QueryParam> querList =null;
			
			if (!StringUtil.isEmpty(queryParams)) {
				querList=JSONArray.toList(JSONArray.fromObject(queryParams),new QueryParam(),new JsonConfig()); 
			}
			
			list = baseGridService.getList("TRole", pagination,querList);
			map.put("total", pagination.getAllCount());
			map.put("rows", list);
		}
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
			TRole obj = (TRole)getEntityById(TRole.class,id);
			StringBuffer sb=new StringBuffer();
			request.setAttribute("o", obj);
			List<TRoleMenu> list=roleService.getList(id);
			for(int i=0;list!=null&&i<list.size();i++){
				sb.append(list.get(i).getMenuId()).append(",");
			}
			request.setAttribute("checkedList",sb.length()>0?sb.substring(0,sb.length()-1):"");
		}
		//获得全部菜单功能
		request.setAttribute("tabsList",menuService.getMenuList02("","T"));
		request.setAttribute("parentList", menuService.getParentMenu(""));
		request.setAttribute("childList",menuService.getAllMenuItemList());
		return "system/role/roleDetail";
	}
	/**
	 * 保存或者新增
	 * @param pojo
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public Map save(HttpServletRequest request,TRole obj,BindingResult errorResult) {
		String[] checkItem=request.getParameterValues("checkItem");
		TRoleMenu troleMenu=new TRoleMenu();
		Map<String,String> msg = new HashMap<String, String>();
		if (StringUtil.isEmpty(obj.getId())) {
			obj.setId(UUID.randomUUID().toString());
			addTRoleMenu(checkItem,troleMenu,obj);
			add(obj);
			msg.put("success", "新增成功");
		}else {
			roleService.deleteByRoleId(obj.getId());
			addTRoleMenu(checkItem,troleMenu,obj);
			update(obj);
			msg.put("success", "修改成功");
		}
		return  msg; 
	}
	
	public void addTRoleMenu(String[] checkItem,TRoleMenu troleMenu,TRole trole){
		if(checkItem!=null){
			for(int i=0;i<checkItem.length;i++){
				troleMenu.setId(UUID.randomUUID().toString());
				troleMenu.setMenuId(checkItem[i]);
				troleMenu.setRoleId(trole.getId());
				add(troleMenu);
			}
		}
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
		/** 删除之前先判断当前角色有没有被使用**/
		if(roleService.isUsedByRoleId(id)){
			msg.put("error", "该角色已经被使用，无法删除");
		}else{
			roleService.deleteByRoleId(id);
			if (deleteEntityById(TRole.class, id)) {
				msg.put("success", "删除成功");
			}else {
				msg.put("error", "删除失败");
			}
		}
		return  msg; 
	}
	
}

