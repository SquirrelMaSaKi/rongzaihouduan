package com.rj.dao;

import com.rj.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    List<Menu> findBySysUserId(@Param("userId") Integer userId,@Param("menuId") Integer menuId);

    List<Menu> findAll();

    void addRoleAndMenu(@Param("roleId") Integer roleId, @Param("menu") Integer menu);

    List<Integer> findMenuIdsByRoleId(@Param("roleId") Integer roleId);

    void deleteByRoleId(@Param("roleId") Integer roleId);
}
