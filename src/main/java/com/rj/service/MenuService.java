package com.rj.service;


import com.github.pagehelper.PageInfo;
import com.rj.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findBySysUserId(Integer userId);

    List<Menu> findAll();

    PageInfo<Menu> findByPage(String order, Integer limit, Integer offset);

    List<Menu> findBySysUserId2(Integer userId);
}
