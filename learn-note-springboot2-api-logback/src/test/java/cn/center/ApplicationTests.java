package cn.center;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() throws Exception {
		logger.info("123");
		try {
			int i = 1/0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
