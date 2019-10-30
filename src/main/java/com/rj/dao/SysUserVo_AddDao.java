package com.rj.dao;

import com.rj.vo.SysUserVo_Add;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserVo_AddDao {
    SysUserVo_Add findByUserId(@Param("userId") Integer userId);
    List<Integer> roleIdsByUserId(@Param("userId") Integer userId);
    void modify(SysUserVo_Add sysUserVo_add);

}
