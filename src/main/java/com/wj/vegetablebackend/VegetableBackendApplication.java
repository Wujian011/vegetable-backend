package com.wj.vegetablebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wj.vegetablebackend.mapper")
public class VegetableBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VegetableBackendApplication.class, args);
    }

}
