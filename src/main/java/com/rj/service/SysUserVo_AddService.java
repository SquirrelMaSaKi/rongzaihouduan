package com.rj.service;

import com.rj.vo.SysUserVo_Add;
import org.apache.ibatis.annotations.Param;

public interface SysUserVo_AddService {
    SysUserVo_Add findByUserId(@Param("userId") Integer userId);
    void update(SysUserVo_Add sysUserVo_add);
}
