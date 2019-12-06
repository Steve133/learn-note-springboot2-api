package my.master.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author song
 * @title 页面跳转
 * @projectName demo
 * @description TODO
 * @date 2019年11月16日 下午12:53:42
 */
@Controller
public class PageController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
