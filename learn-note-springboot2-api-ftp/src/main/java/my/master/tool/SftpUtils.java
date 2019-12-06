package my.master.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtils {
	private static Logger logger = LoggerFactory.getLogger(SftpUtils.class);
	private static JSch jsch;
	private static Session session = null;
	private static Channel channel = null;

	/**
	 * 连接到指定的IP
	 * 
	 * @throws JSchException
	 */
	public static ChannelSftp connect(String ftpUserName, String ftpPassword, String ftpHost, int ftpPort) throws Exception {
		jsch = new JSch();// 创建JSch对象
		session = jsch.getSession(ftpUserName, ftpHost, ftpPort);// 根据用户名、主机ip、端口号获取一个Session对象
		session.setPassword(ftpPassword);// 设置密码

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);// 为Session对象设置properties
		session.setTimeout(1000 * 30);// 设置超时
		session.connect();// 通过Session建立连接
		logger.info("Session connected.");
		channel = session.openChannel("sftp"); // 打开SFTP通道
		channel.connect(); // 建立SFTP通道的连接
		logger.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName);
		return (ChannelSftp) channel;
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		if (channel != null) {
			channel.disconnect();
			logger.info("关闭channel成功");
		}
		if (session != null) {
			session.disconnect();
			logger.info("关闭session成功");
		}
	}

	/**
	 * 执行相关的命令, 但是部分情况不可用
	 * 
	 * @throws JSchException
	 */
	public static void execCmd(String command) throws JSchException {
		BufferedReader reader = null;

		try {
			if (command != null) {
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);
				// ((ChannelExec) channel).setErrStream(System.err);
				channel.connect();

				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					logger.info(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
		}
	}

	/**
	 * 上传文件
	 *
	 * @param directory  上传的目录
	 * @param uploadFile 要上传的文件
	 * @param sftp
	 * @throws JSchException
	 * @throws SftpException
	 * @throws FileNotFoundException
	 */
	public static void upload(String directory, String uploadFile) throws Exception {
		((ChannelSftp) channel).cd(directory);
		File file = new File(uploadFile);
		((ChannelSftp) channel).put(new FileInputStream(file), file.getName());
		logger.info("上传: " + uploadFile + "成功!");
	}

	/**
	 * 下载文件
	 * 
	 * @param src
	 * @param dst
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static void download(String src, String dst) throws Exception {
		// src linux服务器文件地址，dst 本地存放地址
		((ChannelSftp) channel).connect();
		((ChannelSftp) channel).get(src, dst);
		logger.info("下载文件：" + src + "成功");
		((ChannelSftp) channel).quit();
	}

	/**
	 * 删除文件
	 *
	 * @param directory  要删除文件所在目录
	 * @param deleteFile 要删除的文件
	 * @param sftp
	 * @throws SftpException
	 * @throws JSchException
	 */
	public void delete(String directory, String deleteFile) throws Exception {
		((ChannelSftp) channel).cd(directory);
		((ChannelSftp) channel).rm(deleteFile);
		logger.info("删除成功");
	}

	/**
	 * 列出目录下的文件
	 *
	 * @param directory 要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 * @throws JSchException
	 */
	@SuppressWarnings("rawtypes")
	public static Vector listFiles(String directory) throws Exception {
		return ((ChannelSftp) channel).ls(directory);
	}

	public static void main(String[] args) {
		try {
			// 1.连接到指定的服务器
			connect("root", "alpine", "192.168.1.104", 22);
//			upload("/var/mobile/Media/TouchSprite/lua/", "D:\\aaa.lua");
			Vector listFiles = listFiles("/var/mobile/Media/TouchSprite/lua/");
			for (Object item : listFiles) {
				ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) item;
				System.out.println(entry.getFilename());
			}
//			// 2.执行相关的命令
//			execCmd("grep '160622150549943666' /data/apps/2017-07-07.log >> /data/20170707.txt");
//
//			// 3.下载文件
//			download("/data/nginx_log.20170707.txt", "D:\\temp");

			// 4.关闭连接
			close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
