package com.rj.vo;

import com.rj.pojo.SysUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class SysUserVo extends SysUser {
    private String captcha;
    private String rememberMe;
}
