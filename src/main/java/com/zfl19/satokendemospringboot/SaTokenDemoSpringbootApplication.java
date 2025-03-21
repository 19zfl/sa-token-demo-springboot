package com.zfl19.satokendemospringboot;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zfl19.satokendemospringboot.mapper")
public class SaTokenDemoSpringbootApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(SaTokenDemoSpringbootApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
