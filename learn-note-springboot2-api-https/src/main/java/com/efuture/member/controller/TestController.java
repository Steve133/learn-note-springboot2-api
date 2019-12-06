package com.efuture.member.controller;

import java.math.BigDecimal;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @后台管理登陆
 * @author :陈进松
 * @date :2019年4月4日 下午2:48:35
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/test")
	public String test() throws Exception {
		return "success";
	}
}