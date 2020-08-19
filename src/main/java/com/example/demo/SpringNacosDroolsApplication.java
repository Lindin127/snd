package com.example.demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo.*"})
@MapperScan(basePackages = {"com.example.demo.dao"})
@NacosPropertySource(dataId = "spring_drools.yaml", autoRefreshed = true)
public class SpringNacosDroolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNacosDroolsApplication.class, args);
	}

}
