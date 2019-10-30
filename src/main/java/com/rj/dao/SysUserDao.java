package com.rj.dao;

import com.rj.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {
    SysUser findByUsername(@Param("username") String username);
    void add(SysUser sysUser);
    List<SysUser> findAll();

    void deleteByUserId(@Param("userId") Integer userId);
}
