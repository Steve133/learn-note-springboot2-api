package cn.center.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @页面跳转
 * @author :陈进松
 * @date :2019年4月3日 下午10:35:45
 */
@Controller
public class PageController {

	/**
	 * 打开首页
	 */
	@RequestMapping("/")
	public String showIndex(HttpServletRequest request) {
		return "forward:sso/admin/login";
	}

	/**
	 * 展示其他页面
	 */
	@RequestMapping("/{page}")
	public String showpage(HttpServletRequest request, @PathVariable String page) {
		return page;
	}
	@RequestMapping("/page/{page}")
	public String showpage2(HttpServletRequest request, @PathVariable String page) {
		return "page/"+page;
	}
	@RequestMapping("/admin/welcome")
	public String welcome() {
		return "adminWelcome";
	}
}
