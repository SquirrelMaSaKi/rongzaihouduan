package com.rj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rj.dao.MenuDao;
import com.rj.pojo.Menu;
import com.rj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findBySysUserId(Integer userId) {
        List<Menu> menus = menuDao.findBySysUserId(userId,0);
        for (Menu menu : menus) {
            List<Menu> sons = menuDao.findBySysUserId(userId, menu.getMenuId());
            menu.setList(sons);
        }
        return menus;
    }

    @Override
    public List<Menu> findAll() {
        List<Menu> menus = menuDao.findAll();
        return menus;
    }

    @Override
    public PageInfo<Menu> findByPage(String order, Integer limit, Integer offset) {
        PageHelper.offsetPage(offset, limit);
        if(order.equals("asc")) {
            PageHelper.orderBy("menu_id asc");
        } else if(order.equals("desc")) {
            PageHelper.orderBy("menu_id desc");
        }
        List<Menu> list = menuDao.findAll();
        PageInfo<Menu> pageInfo = PageInfo.of(list);
        return pageInfo;
    }

    @Override
    public List<Menu> findBySysUserId2(Integer userId) {
        return menuDao.findBySysUserId2(userId);
    }
}
