package com.acloudtiger.springcloudsecurityresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableResourceServer
@EnableSwagger2
public class SpringCloudSecurityResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSecurityResourceServerApplication.class, args);
    }
}
