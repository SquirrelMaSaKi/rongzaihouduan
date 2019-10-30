package com.rj.service;


import com.github.pagehelper.PageInfo;
import com.rj.pojo.SysUser;

public interface SysUserService {
    SysUser findByUsername(String username);
    void add(SysUser sysUser);
    PageInfo<SysUser> findAll(String order, Integer limit, Integer offset);

    void deleteByUserId(Integer userId);
}
