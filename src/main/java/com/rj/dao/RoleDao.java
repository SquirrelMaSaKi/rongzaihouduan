package com.rj.dao;

import com.rj.vo.Role_MenuVo;
import org.apache.ibatis.annotations.Param;

import com.rj.pojo.Role;
import java.util.List;
import java.util.Set;

public interface RoleDao {
    Set<String> roles(@Param("userId") Integer userId);

    List<Role> findAll();

    void addRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    void deleteByUserId(@Param("userId") Integer userId);

    void addNewRole(Role_MenuVo role_menuVo);

    Role_MenuVo findByRoleId(Integer roleId);

    void modify(Role_MenuVo role_menuVo);

    void deleteByRoleId(@Param("roleId") Integer roleId);
}
