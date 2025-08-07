package com.yzkj.starter;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = {"com.yzkj.starter"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.yzkj.starter.mapper"})
@EnableDubbo
public class MvcStarterApplication {
    public static void main(String[] args) throws UnknownHostException {
        // 添加环境变量
        System.setProperty("spring.cloud.boostrap.enabled", "true");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MvcStarterApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        System.out.println(
                "\n\t" +
                        "--------------------------------------\n\t" +
                        "Application is running! Access URLs: \n\t" +
                        "Local: \t\t http://" + ip +":" + port + "/\n\t"
        );
    }
}