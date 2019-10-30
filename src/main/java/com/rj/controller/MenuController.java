package com.rj.controller;

import com.github.pagehelper.PageInfo;
import com.rj.pojo.Menu;
import com.rj.service.MenuService;
import com.rj.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(value = "http://localhost:8989")
@RequestMapping("/sys")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/list")
    @ResponseBody
    public R menuList(String order, Integer limit, Integer offset) {
        PageInfo<Menu> pageBean = menuService.findByPage(order,limit,offset);
        return R.ok().put("total", pageBean.getTotal()).put("rows", pageBean.getList());
    }
}
