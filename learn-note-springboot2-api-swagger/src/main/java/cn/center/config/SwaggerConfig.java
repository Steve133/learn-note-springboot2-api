package cn.center.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 中文 http://127.0.0.1:8080/swagger/index.html<br>
 * 
 * 默认  http://127.0.0.1:8080/swagger-ui.html<br>
 * 
 * 在浏览器：http://127.0.0.1:8080/v2/api-docs  生成  swagger.yaml 文件内容<br>
 * 
 * 描述: 开始 swagger2<br>
 * @author :陈进松
 * @date :2019年10月21日 下午10:28:41
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.center.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用 Swagger2构建 RESTfulAPI 文档")
                .description("更多 Spring Boot 相关文章 http://www.ymq.io")
                .termsOfServiceUrl("http://www.ymq.io")
                .version("V1.0")
                .build();
    }
}