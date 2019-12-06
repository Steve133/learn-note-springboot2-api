package cn.center.manager.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.center.common.constant.Const;
import cn.center.common.utils.VerifyCodeUtils;

/**
 * 图片验证码
 * @author :陈进松
 * @date :2019年10月3日 下午2:38:53
 */
@Controller
@RequestMapping("sso")
public class AuthImagesController {
	private static final Logger logger = LoggerFactory.getLogger(AuthImagesController.class);

	/**
	 * @param id       随机数
	 * @param response
	 */
	@RequestMapping(value = "/verify/image", method = RequestMethod.GET)
	public void verifyimage(String id, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		logger.info(" Image key : {}", Const.ADMIN_VERIFYCODE+id);
		logger.info(" Image verifyCode : {}", verifyCode);

		// 生成图片
		int w = 100, h = 30;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			logger.error("验证码生成失败", e);
		}
	}

}
