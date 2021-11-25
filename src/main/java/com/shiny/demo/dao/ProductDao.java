package com.shiny.demo.dao;

import com.shiny.demo.dto.QueryParam;
import com.shiny.demo.entity.Product;

import java.util.List;

public interface ProductDao {

    //查分页数据
    List<Product> findAll(QueryParam queryParam);

    //查数量
    Integer getCount(QueryParam queryParam);

    int addProduct(Product product);

    void deleteProductById(Integer productId);

    Product findById(int id);

    int updateProduct(Product product);

}
