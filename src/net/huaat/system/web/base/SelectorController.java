package net.huaat.system.web.base;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.huaat.common.service.BaseGridService;
import net.huaat.common.util.StringUtil;
import net.huaat.common.web.BaseController;
import net.huaat.system.service.base.SelectorService;
import net.huaat.system.util.easyui.TreeNode;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**  
 * @Description: Common data selector<br>Such as grid,tree and so on;
 * @author zhiqiang yang 
 * @date 2013-3-12 下午3:00:31
 * @version V1.0  
 */
@Controller
@RequestMapping("/base/selector")
public class SelectorController extends BaseController{
	@Resource
	private SelectorService selectorService;
	@Resource
	private BaseGridService baseGridService;
	
	@RequestMapping(value="/combotree",method=RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> combotree(String id,String opts) {
		JSONObject optsObject = JSONObject.fromObject(opts);
		String pojoName = optsObject.getString("pojo");
		List list = new ArrayList();
		if ("false".equals(optsObject.getString("cascade"))) {
			list = baseGridService.getList(pojoName);
		}else {
			list = selectorService.getListByParentId(pojoName,id,optsObject.getString("pFieldName"));  
		}
		List<TreeNode> nodes = listToNodes(list,optsObject.getString("async"),optsObject); 
		
		return nodes ; 
	}
	/**
	 * 将树模型的list数据转换到treenode对象中
	 * @param typeList
	 * @param async 是否异步加载数据
	 * @return
	 */
	private List<TreeNode> listToNodes(List<?> list,String async,JSONObject node){
		 List<TreeNode> nodes = new ArrayList<TreeNode>();
		 String cascade =node.getString("cascade");
		 try {
			 Class class1 = Class.forName(node.getString("pojo")); 
			 Method getIdMethod = class1.getDeclaredMethod("get"+StringUtil.upperCaseFirstCharacter(node.getString("id")));
			 Method getNameMethod = class1.getDeclaredMethod("get"+StringUtil.upperCaseFirstCharacter(node.getString("text")));
			 
			 for(Object o:list){
				 TreeNode treeNode = new TreeNode(); 
				 treeNode.setId(getIdMethod.invoke(o).toString());
				 treeNode.setText(getNameMethod.invoke(o).toString());
				 //If cascade 
				 if ("true".equals(cascade)){
					 try {
						 Method isParentMethod = class1.getDeclaredMethod("getIsParent");
						 if ("Y".equals(isParentMethod.invoke(o).toString())) {
							 //If asynchrony,then get children;
							 treeNode.setState("closed");
							 if ("false".equals(async)) {
								 treeNode.setChildren(listToNodes(selectorService.getListByParentId(node.getString("pojo"),treeNode.getId(),node.getString("pFieldName")),async,node)); 
							 }
						 }
					} catch (Exception e) {
						treeNode.setState("closed");
					}
				 }
				 nodes.add(treeNode);
			 }
			
		} catch (Exception e) {
			log.error("parse error:"+e.getMessage());
		}
		return nodes;
	}
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String init(String type,String url,String callback,String singleSelect,Model model) {
		
		model.addAttribute("url", url);
		
		model.addAttribute("callback",callback);
		model.addAttribute("singleSelect", singleSelect);
		return "system/base/selector";
	}
}

