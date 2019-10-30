package com.rj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * CREATE TABLE `sys_role` (
 *   `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
 *   `remark` varchar(100) DEFAULT NULL COMMENT '备注',
 *   `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   PRIMARY KEY (`role_id`)
 * )
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer roleId;
    private String roleName;
    private String remark;
    private Integer createUserId;
    private Date createTime;
}
