package net.huaat.common.util;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**  
 * @Description: performing data binding in the context of a specific web request
 * @author zhiqiang yang 
 * @date 2012-10-10 下午5:03:23
 * @version V1.0  
 */
public class MyWebBinding  implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		 binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}

}

