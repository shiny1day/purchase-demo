package com.shiny.demo.controller;

import com.shiny.demo.dao.ProductDao;
import com.shiny.demo.dao.UserDao;
import com.shiny.demo.entity.Product;
import com.shiny.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/login")//主页
    public String index() {
        return "login";
    }

    @RequestMapping("/goRegister")//去注册页面
    public String goRegister() {
        return "register";
    }

    @RequestMapping("/register")//注册
    @ResponseBody
    public boolean register(User user) {
        int i = userDao.addUser(user);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/goLogin")//登录获取用户信息存到seccion
    public String goLogin(User user, HttpServletRequest request, Model model) {
        User u = userDao.login(user);
        if (u == null) {
            return "public/false";
        }
        HttpSession session = request.getSession();
        session.setAttribute("aname", u.getName());
        session.setAttribute("apassword", u.getPassword());
        model.addAttribute("admin", u);
        return "product/index";
    }

    @RequestMapping("/index")//从其他页面操作后返回列表页面（重复登录）
    public String login(User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user.setName((String) session.getAttribute("aname"));
        user.setPassword((String) session.getAttribute("apassword"));
        if (user.getName() == null || user.getPassword() == null) {
            return "login";
        }
        User u = userDao.login(user);
        model.addAttribute("admin", u);
        return "product/index";
    }

    @RequestMapping("/goAdd")//去添加页面
    public String goAdd() {
        return "product/add";
    }

    @RequestMapping("/goUpdate/{id}")//去修改页面，回显数据
    public String goUpdate(@PathVariable("id") int id, Model model) {
        Product product = productDao.findById(id);
        model.addAttribute("pro", product);
        return "product/update";
    }

}
