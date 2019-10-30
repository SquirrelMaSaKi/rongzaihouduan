package com.rj.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private Integer createUserId;
    private Date createTime;
    private Integer deptId;
    private String sex;
    private Date lockdate;
}
