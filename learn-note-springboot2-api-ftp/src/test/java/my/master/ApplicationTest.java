package my.master;

import java.util.Properties;
import java.util.Vector;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import cn.master.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
	private static Logger logger = LoggerFactory.getLogger(ApplicationTest.class);


	public static void main(String[] args) throws Exception {
		ChannelSftp sftp = null;
		Channel channel = null;
		Session sshSession = null;

		String username = "root";
		String host = "192.168.1.104";
		String password = "alpine";
		int port = 22;

		try {
			JSch jsch = new JSch();
//			jsch.getSession(username, host, port);
			sshSession = jsch.getSession(username, host, port);
			sshSession.setPassword(password);
			
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.info("连接成功");
			channel = sshSession.openChannel("sftp");
			channel.connect();
			
			sftp = (ChannelSftp) channel;
			Vector vector = sftp.ls("/private/var/root/");
			for (Object item : vector) {
				ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) item;
				System.out.println(entry.getFilename());
			}
		} catch (JSchException e) {
            e.printStackTrace();
		} catch (SftpException e) {
            e.printStackTrace();
        }finally {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
        }
	}
	private static void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private static void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
