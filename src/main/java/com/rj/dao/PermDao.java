package com.rj.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermDao {
    List<String> perms(@Param("userId") Integer userId);
}
