package com.shiny.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shiny.demo.dao")
public class PurchaseApp {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseApp.class, args);
    }

}
