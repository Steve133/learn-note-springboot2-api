package cn.master.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import cn.master.service.PictureService;
import my.master.tool.FastDFSClient;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {

	@Override
	public String uploadFile(byte[] fileContent, String extName) throws Exception {
		//把图片上传的图片服务器
		String filePath = new ClassPathResource("fastdfs/fastdfs.conf").getFile().getAbsolutePath();
		FastDFSClient fastDFSClient = new FastDFSClient(filePath);
		//得到一个图片的地址和文件名
		return fastDFSClient.uploadFile(fileContent, extName);
	}

}
