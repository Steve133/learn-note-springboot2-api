package com.efuture.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * jar 打包方式
 * @author :
 * @date :2019年10月13日 下午8:28:38
 */
@SpringBootApplication
@ComponentScan(value = {"com"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}



/**
 * war 打包方式
 * <br>
 * 注释pom springboot-tomcat
 * @author :
 * @date :2019年10月13日 下午8:29:54
 */
//@SpringBootApplication
//@ComponentScan(value = {"com.efuture.member"})
//public class Application extends SpringBootServletInitializer{
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }
//	
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//}