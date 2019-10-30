package com.rj.service;

import com.github.pagehelper.PageInfo;
import com.rj.pojo.Role;
import com.rj.vo.Role_MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<String> roles(@Param("userId") Integer userId);
    List<Role> findAll();
    PageInfo<Role> findAllByPage(String order, Integer limit, Integer offset);
    void addRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
    void deleteByUserId(@Param("userId") Integer userId);

    void addNewRole(Role_MenuVo role_menuVo);

    Role_MenuVo findByRoleId(Integer roleId);

    void modify(Role_MenuVo role_menuVo);

    void deleteByRoleId(Integer roleId);
}
