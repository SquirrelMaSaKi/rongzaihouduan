package com.rj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CREATE TABLE `sys_menu` (
 *   `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
 *   `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
 *   `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
 *   `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
 *   `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
 *   `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
 *   `order_num` int(11) DEFAULT NULL COMMENT '排序',
 *   PRIMARY KEY (`menu_id`)
 * )
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer menuId;
    private Integer parentId;
    private String name;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;
    private List<Menu> list;

    public Menu(Integer menuId, Integer parentId, String name, String url, String perms, Integer type, String icon, Integer orderNum) {
        this.menuId = menuId;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.orderNum = orderNum;
    }
}
