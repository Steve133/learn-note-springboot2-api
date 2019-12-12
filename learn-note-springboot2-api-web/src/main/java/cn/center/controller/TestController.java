package cn.center.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cn.center.http.HttpClientUtils;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


@Controller
public class TestController {
	
	@ApiOperation(value = "测试", notes = "测试")
	@GetMapping("/")
	public String post(String url,JSONObject data) throws Exception {
		return HttpClientUtils.doPost(url, data , null, 50000, 10000, 50000);
	}

}
