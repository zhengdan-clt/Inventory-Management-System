package com.pn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//开启redis注解版缓存
@EnableCaching
//mapper接口扫描器，指明mapper接口所在包，自动为mapper接口创建代理对象并加入到IOC容器
@MapperScan(basePackages = {"com.pn.mapper", "com.pn.utils"})
@SpringBootApplication
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
