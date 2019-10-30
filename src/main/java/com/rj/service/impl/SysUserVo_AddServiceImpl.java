package com.rj.service.impl;

import com.rj.dao.RoleDao;
import com.rj.dao.SysUserVo_AddDao;
import com.rj.service.SysUserVo_AddService;
import com.rj.vo.SysUserVo_Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysUserVo_AddService")
public class SysUserVo_AddServiceImpl implements SysUserVo_AddService {
    @Autowired
    private SysUserVo_AddDao sysUserVo_addDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public SysUserVo_Add findByUserId(Integer userId) {
        SysUserVo_Add sysUserVo_add = sysUserVo_addDao.findByUserId(userId);
        List<Integer> roleIds = sysUserVo_addDao.roleIdsByUserId(sysUserVo_add.getUserId());
        sysUserVo_add.setRoles(roleIds);
        return sysUserVo_add;
    }

    @Override
    public void update(SysUserVo_Add sysUserVo_add) {
        sysUserVo_addDao.modify(sysUserVo_add);
        roleDao.deleteByUserId(sysUserVo_add.getUserId());
        List<Integer> roles = sysUserVo_add.getRoles();
        for (Integer role : roles) {
            roleDao.addRole(sysUserVo_add.getUserId(), role);
        }
    }
}
