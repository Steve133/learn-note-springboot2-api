package my.master.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @页面跳转
 * @author :陈进松
 * @date :2019年4月3日 下午10:35:45
 */
@Controller
public class PageController {
	
//	@RequestMapping(value = "/manhua", method = RequestMethod.GET)
//	public String table(HttpServletRequest request, Model model,String DATA,String nonce) {
//		model.addAttribute("DATA", DATA);
//		model.addAttribute("nonce", nonce);
//		return "/txManHua";
//	}
	@RequestMapping(value = "/manhua", method = RequestMethod.GET)
	public String manhua(String data,String nonce,Model model) {
		System.out.println(data);
		System.out.println(nonce);
		model.addAttribute("data", data);
		model.addAttribute("nonce", nonce);
		return "txManHua";
	}

}
