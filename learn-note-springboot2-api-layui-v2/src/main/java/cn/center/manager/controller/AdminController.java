package cn.center.manager.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.center.common.constant.Const;
import cn.center.common.utils.CookieUtils;
import cn.center.common.utils.E3Result;

/**
 * @后台管理登陆
 * @author :陈进松
 * @date :2019年4月4日 下午2:48:35
 */
@Controller
@RequestMapping("sso/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping("/")
	public String index() {
		return "sso/admin/login";
	}
	@RequestMapping("/login")
	public String login(Model model) {
		//放入token
		model.addAttribute("id", UUID.randomUUID().toString().replaceAll("-", ""));
		//放入后台网址
		model.addAttribute("admin_web_url", "");
		return "admin/login";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public E3Result login(String userName, String userPwd, String code , String id,  HttpServletRequest request, HttpServletResponse response) {
		return E3Result.ok();
	}
	
	/**
     * 用户注销
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,Model model) {
    	String token = CookieUtils.getCookieValue(request, Const.ADMIN_COOKIE_LOGIN_KEY);
    	CookieUtils.deleteCookie(request, response, Const.ADMIN_COOKIE_LOGIN_KEY);
    	
    	response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));//指定允许其他域名访问
		response.addHeader("Access-Control-Allow-Credentials", "true");// 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名
		
		model.addAttribute("id", UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("admin_web_url", "");
		return "admin/login";
    }
}