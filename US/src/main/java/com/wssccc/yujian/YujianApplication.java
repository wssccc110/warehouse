package com.wssccc.yujian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wssccc.yujian.dao")
public class YujianApplication {

	public static void main(String[] args) {
		SpringApplication.run(YujianApplication.class, args);
	}
}
