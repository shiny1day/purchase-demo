package com.shiny.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;

    private String name;

    private Double price;

    private String spec;

    private String desc;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}
