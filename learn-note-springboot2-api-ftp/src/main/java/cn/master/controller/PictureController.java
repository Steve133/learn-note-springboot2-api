package cn.master.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.master.service.PictureService;
import my.master.tool.E3Result;

/**
 * @图片上传处理
 * @author :陈进松
 * @date :2019年4月3日 下午10:37:56
 */
@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@ResponseBody
	@RequestMapping(value="/pic/upload",method=RequestMethod.POST)
	public E3Result uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			String module = request.getParameter("module");
			if(StringUtils.isEmpty(module)) {
            	return E3Result.build(500, "module_is_blank", "false");
			}
            if(file==null || file.isEmpty()){
            	return E3Result.build(500, "file_not_found", "false");
            }
			//取文件扩展名
			String originalFilename = file.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			//得到一个图片的地址和文件名
			String url = pictureService.uploadFile(file.getBytes(), extName);
			//补充为完整的url
			url = IMAGE_SERVER_URL + url;
			return E3Result.ok(url);
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "图片上传失败", "false");
		}
	}
}
