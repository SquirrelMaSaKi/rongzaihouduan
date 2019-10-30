package com.rj.controller;

import com.github.pagehelper.PageInfo;
import com.rj.pojo.Menu;
import com.rj.pojo.Role;
import com.rj.pojo.SysUser;
import com.rj.service.MenuService;
import com.rj.service.RoleService;
import com.rj.service.SysUserService;
import com.rj.utils.R;
import com.rj.vo.Role_MenuVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(value = "http://localhost:8989")
@RequestMapping("/sys")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/roles")
    @ResponseBody
    public R roles(){
        List<Role> roles = roleService.findAll();
        return R.ok().put("roles", roles);
    }

    @RequestMapping("/role/list")
    @ResponseBody
    public R roleList(String order, Integer limit, Integer offset){
        PageInfo<Role> pageBean = roleService.findAllByPage(order,limit,offset);
        return R.ok().put("total", pageBean.getTotal()).put("rows", pageBean.getList());
    }

    @GetMapping("/menu/select")
    @ResponseBody
    public R menuSelect() {
        List<Menu> menus = menuService.findAll();
        return R.ok().put("menuList", menus);
    }

    @PostMapping("/role/save")
    @ResponseBody
    public R roleSave(@RequestBody Role_MenuVo role_menuVo) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        SysUser sysUser = sysUserService.findByUsername(username);
        //创建者id
        role_menuVo.setCreateUserId(sysUser.getUserId());
        //创建时间
        role_menuVo.setCreateTime(new Date());
        //添加
        roleService.addNewRole(role_menuVo);
        return R.ok();
    }

    @GetMapping("/role/info/{roleId}")
    @ResponseBody
    public R roleInfo(@PathVariable("roleId") Integer roleId) {
        Role_MenuVo role_menuVo = roleService.findByRoleId(roleId);
        return R.ok().put("role", role_menuVo);
    }

    @PostMapping("/role/update")
    @ResponseBody
    public R roleUpdate(@RequestBody Role_MenuVo role_menuVo) {
        roleService.modify(role_menuVo);
        return R.ok();
    }

    @DeleteMapping("/roles/{roleId}")
    @ResponseBody
    public R rolesDelete(@PathVariable("roleId") Integer roleId) {
        try {
            roleService.deleteByRoleId(roleId);
            return R.ok();
        } catch (Exception e) {
            return R.error("删除失败");
        }
    }

}
