package com.shiny.demo.controller;

import com.shiny.demo.dao.ProductDao;
import com.shiny.demo.dto.DataTable;
import com.shiny.demo.dto.QueryParam;
import com.shiny.demo.dto.ResultMsg;
import com.shiny.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @PostMapping("/list")//从其他页面操作后返回列表页面（重复登录）
    public DataTable userList(@RequestBody QueryParam queryParam) {
        List<Product> list = productDao.findAll(queryParam);
        Integer count = productDao.getCount(queryParam);

        DataTable<Product> studentTable = new DataTable<>();
        studentTable.setTotal(count);
        studentTable.setRows(list);
        return studentTable;
    }

    @PostMapping("/add")
    public ResultMsg addProduct(Product product, HttpServletRequest request) {
        if (StringUtils.isEmpty(product.getName())) {
            return ResultMsg.getErrorResult(null, "名称不能为空！");
        }
        if (Objects.isNull(product.getPrice())) {
            return ResultMsg.getErrorResult(null, "价格不能为空！");
        }
        product.setCreateTime(new Date());
        product.setCreateUser((String) request.getSession().getAttribute("aname"));
        int i = productDao.addProduct(product);
        if (i > 0) {
            return ResultMsg.getSuccessResult(null);
        } else {
            return ResultMsg.getErrorResult(null, "添加失败！");
        }
    }

    @RequestMapping("/update")//修改
    public ResultMsg updateUser(Product product, HttpServletRequest request) {
        if (StringUtils.isEmpty(product.getName())) {
            return ResultMsg.getErrorResult(null, "名称不能为空！");
        }
        if (Objects.isNull(product.getPrice())) {
            return ResultMsg.getErrorResult(null, "价格不能为空！");
        }
        product.setUpdateTime(new Date());
        product.setUpdateUser((String) request.getSession().getAttribute("aname"));
        int i = productDao.updateProduct(product);
        if (i > 0) {
            return ResultMsg.getSuccessResult(null);
        } else {
            return ResultMsg.getErrorResult(null, "修改失败！");
        }
    }

    @DeleteMapping("/del/{productId}")
    public ResultMsg delUser(@PathVariable("productId") Integer productId) {
        productDao.deleteProductById(productId);
        return ResultMsg.getSuccessResult(null);
    }

}
