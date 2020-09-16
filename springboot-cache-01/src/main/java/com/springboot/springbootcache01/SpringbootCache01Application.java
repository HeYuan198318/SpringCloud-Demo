package com.springboot.springbootcache01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 *快速體驗緩存
 *  開啟基於註解的緩存
 *  標註緩存註解
 */
@MapperScan("com.springboot.springbootcache01.mapper")
@SpringBootApplication
@EnableCaching
public class SpringbootCache01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCache01Application.class, args);
    }

}
