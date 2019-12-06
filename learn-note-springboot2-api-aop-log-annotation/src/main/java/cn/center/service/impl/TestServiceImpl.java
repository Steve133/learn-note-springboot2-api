package cn.center.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.center.framework.aspectj.annotation.ServiceAccessLog;
import cn.center.mapper.CsTestMapper;
import cn.center.pojo.CsTest;
import cn.center.pojo.CsTestExample;
import cn.center.service.TestService;

@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private CsTestMapper mapper;

	@ServiceAccessLog(description = "这是一个测试服务")
	@Override
	public void test_query() throws Exception {
		logger.info("test_query...");
//		CsTest tb = new CsTest();
//		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		tb.setClassid("1");
//		tb.setScore(99);
//		tb.setUserid("1111111");
//		mapper.insert(tb);
//		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		tb.setClassid("1");
//		tb.setScore(99);
//		tb.setUserid("1111111");
//		mapper.insert(tb);
//		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		tb.setClassid("1");
//		tb.setScore(99);
//		tb.setUserid("1111111");
//		mapper.insert(tb);
		
		
		
		PageHelper.startPage(1, 2);
		CsTestExample example = new CsTestExample();
		List<CsTest> ret = mapper.selectByExample(example);
		for (CsTest csTest : ret) {
			System.out.println(csTest.getId());
		}
		return;
	}
}
