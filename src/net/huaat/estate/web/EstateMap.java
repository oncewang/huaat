/**
 * 
 */
package net.huaat.estate.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.huaat.common.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-25 下午12:54:05
 * @version V1.0  
 */
@Controller
@RequestMapping("/estateMap")
public class EstateMap  extends BaseController{
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String assessInit(HttpServletRequest request,HttpSession session) {
		
		return "estate/map/mapView";
	}
}
