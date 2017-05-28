/**
 * 
 */
package net.huaat.homepage.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-3 下午11:46:03
 * @version V1.0  
 */
@Controller
@RequestMapping("/homepage")
public class HomepageController {
	@RequestMapping(value="/home/init",method=RequestMethod.GET)
	public String init(HttpServletRequest request) {
		return "homepage/home";
	}
}
