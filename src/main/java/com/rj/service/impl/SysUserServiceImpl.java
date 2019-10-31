package com.rj.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rj.dao.RoleDao;
import com.rj.dao.SysUserDao;
import com.rj.pojo.SysUser;
import com.rj.service.SysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public void add(SysUser sysUser) {
        String password = new Md5Hash(sysUser.getPassword()).toBase64();
        sysUser.setPassword(password);
        sysUserDao.add(sysUser);
    }

    @Override
    public PageInfo<SysUser> findAll(String order, Integer limit, Integer offset) {
        PageHelper.offsetPage(offset, limit);

        if(order.equals("asc")){
            PageHelper.orderBy("user_id asc");
        } else if(order.equals("desc")) {
            PageHelper.orderBy("user_id desc");
        }

        List<SysUser> list = sysUserDao.findAll();
        PageInfo<SysUser> pageInfo = PageInfo.of(list);
        return pageInfo;
    }

    @Override
    public void deleteByUserId(Integer userId) {
        sysUserDao.deleteByUserId(userId);
    }

    @Override
    public List<SysUser> querySysUsers() {
        return sysUserDao.findAll();
    }

    @Override
    public void unlockUser(Integer userId) {
        sysUserDao.unlockUser(userId);
    }

}
