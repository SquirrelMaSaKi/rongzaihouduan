package com.rj.vo;

import com.rj.pojo.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SysUserVo_Add extends SysUser {
    private List<Integer> roles;
}
