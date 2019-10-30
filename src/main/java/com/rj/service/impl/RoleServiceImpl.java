package com.rj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rj.dao.MenuDao;
import com.rj.dao.RoleDao;
import com.rj.pojo.Role;
import com.rj.service.RoleService;
import com.rj.vo.Role_MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public Set<String> roles(Integer userId) {
        return roleDao.roles(userId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public PageInfo<Role> findAllByPage(String order, Integer limit, Integer offset) {
        PageHelper.offsetPage(offset, limit);
        if(order.equals("asc")){
            PageHelper.orderBy("role_id asc");
        } else if(order.equals("desc")) {
            PageHelper.orderBy("role_id desc");
        }
        List<Role> list = roleDao.findAll();
        PageInfo<Role> pageInfo = PageInfo.of(list);
        return pageInfo;
    }

    @Override
    public void addRole(Integer userId, Integer roleId) {
        roleDao.addRole(userId, roleId);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        roleDao.deleteByUserId(userId);
    }

    @Override
    public void addNewRole(Role_MenuVo role_menuVo) {
        roleDao.addNewRole(role_menuVo);
        List<Integer> menus = role_menuVo.getMenus();
        for (Integer menu : menus) {
            menuDao.addRoleAndMenu(role_menuVo.getRoleId(),menu);
        }
    }

    @Override
    public Role_MenuVo findByRoleId(Integer roleId) {
        Role_MenuVo role_menuVo = roleDao.findByRoleId(roleId);
        List<Integer> menus = menuDao.findMenuIdsByRoleId(roleId);
        role_menuVo.setMenus(menus);
        return role_menuVo;
    }

    @Override
    public void modify(Role_MenuVo role_menuVo) {
        roleDao.modify(role_menuVo);
        //更新menu
        menuDao.deleteByRoleId(role_menuVo.getRoleId());
        List<Integer> menus = role_menuVo.getMenus();
        for (Integer menu : menus) {
            menuDao.addRoleAndMenu(role_menuVo.getRoleId(), menu);
        }
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        //删除role_menu中的roleId
        menuDao.deleteByRoleId(roleId);
        //删除role
        roleDao.deleteByRoleId(roleId);
    }
}
