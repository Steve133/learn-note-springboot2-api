package my.master.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/**
 * @author song
 * @title 文件处理接口
 * @projectName demo
 * @description TODO
 * @date 2019年11月16日 下午12:53:53
 */
@RestController
public class FileUploadController {

	// 上传文件
	@PostMapping("/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		if (file == null) {
            throw new RuntimeException("上传文件不能为空");
        }
		String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new RuntimeException("上传文件格式错误，请上传后缀为.xls或.xlsx的文件");
        }
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream, 0);
        List<Map<String, Object>> maps = reader.readAll();
        System.out.println(maps);
		return "File is upload successfully";
	}
	
	// 上传文件
	@PostMapping(value = "/uploadSave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadSave(@RequestParam("file") MultipartFile file) throws IOException {
		File convertFile = new File("/var/tmp/" + file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return "File is upload successfully";
	}

	
	
	/**
	 * @description: 适用于war包启动方式下载
	 * @return
	 * @throws IOException
	 * @author song
	 * @date 2019年11月16日 下午1:33:15
	 */
	@GetMapping("/downloadWar")
	public ResponseEntity<Object> downloadWar() throws IOException {
		File file = ResourceUtils.getFile("classpath:static/template/template.xls");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(file.getName(), "utf-8")));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}
	/**
	 * @description: 适用于jar包启动方式下载
	 * @return
	 * @throws IOException
	 * @author song
	 * @date 2019年11月16日 下午1:33:15
	 */
	@GetMapping("/downloadJar")
	public ResponseEntity<Object> downloadJar() throws IOException {
		//获取容器资源解析器
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//获取所有匹配的文件
        Resource resources = resolver.getResource("static/template/template.xls");
        
        InputStream in = resources.getInputStream();
        InputStreamResource resource = new InputStreamResource(in);
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(template.getFilename(), "utf-8")));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(template.contentLength()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}
	
	
	@Value("classpath:static/template/template.xls")
    private Resource template;
	
	@GetMapping("/downloadTemplate")
	public ResponseEntity<Object> downloadTemplate() throws IOException {
		InputStream in = template.getInputStream();
		InputStreamResource resource = new InputStreamResource(in);
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(template.getFilename(), "utf-8")));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(template.contentLength()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}
}