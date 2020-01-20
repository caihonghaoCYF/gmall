package com.atguigu.gmall.wms.conf;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean("仓储平台")
    public Docket userApis(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("仓储平台").select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/wms.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(true);
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("益邦商城")
                .description("提供仓储文档")
                .termsOfServiceUrl("http://www.hao.com")
                .version("1.0")
                .build();
    }


}
