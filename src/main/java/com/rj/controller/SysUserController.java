package com.rj.controller;

import com.github.pagehelper.PageInfo;
import com.rj.pojo.Menu;
import com.rj.pojo.SysUser;
import com.rj.service.*;
import com.rj.utils.R;
import com.rj.utils.StringUtil;
import com.rj.vo.SysUserVo;
import com.rj.vo.SysUserVo_Add;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(value = "http://localhost:8989")
@RequestMapping("/sys")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermService permService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysUserVo_AddService sysUserVo_addService;

    @PostMapping("/login")
    @ResponseBody
    public R loginLogic(@RequestBody SysUserVo sysUserVo, HttpSession session) {
        String orginCaptcha = (String) session.getAttribute("captcha");
        if(!sysUserVo.getCaptcha().equalsIgnoreCase(orginCaptcha)){
            return R.error("验证码输入错误");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(sysUserVo.getUsername(), sysUserVo.getPassword());
        if(!StringUtil.isEmpty(sysUserVo.getRememberMe())){
            token.setRememberMe(true);
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return R.ok();
    }

    @GetMapping("/user/info")
    @ResponseBody
    public R userInfo(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        SysUser sysUser = sysUserService.findByUsername(username);
        return R.ok().put("user", sysUser);
    }

    @GetMapping("/menu/user")
    @ResponseBody
    public R menuUser(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = sysUserService.findByUsername(username);
        //主菜单和子菜单集合
        //List<Menu> menuList = menuService.findBySysUserId(sysUser.getUserId());
        List<Menu> menuList = menuService.findBySysUserId2(sysUser.getUserId());
        //权限集合
        Set<String> perms = permService.perms(sysUser.getUserId());
        return R.ok().put("menuList", menuList).put("permissions", perms);
    }

    @GetMapping("/logout")
    @ResponseBody
    public R logoutLogic() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.ok();
    }

    @GetMapping("/user/list")
    @ResponseBody
    public R userList(String order, Integer limit, Integer offset){
        PageInfo<SysUser> pageBean = sysUserService.findAll(order, limit, offset);
        return R.ok().put("total", pageBean.getTotal()).put("rows", pageBean.getList());
    }

    @PostMapping("/user/save")
    @ResponseBody
    public R userSave(@RequestBody SysUserVo_Add sysUserVo_add){
        //获取登陆者
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        SysUser sysUser = sysUserService.findByUsername(username);

        //时间导入
        sysUserVo_add.setCreateTime(new Date());
        //创建者id导入
        sysUserVo_add.setCreateUserId(sysUser.getUserId());

        //添加用户
        sysUserService.add(sysUserVo_add);
        //将用户id绑定角色id
        if(sysUserVo_add.getRoles() != null && sysUserVo_add.getRoles().size()!=0){
            List<Integer> roles = sysUserVo_add.getRoles();
            for (Integer role : roles) {
                roleService.addRole(sysUserVo_add.getUserId(), role);
            }
        }
        return R.ok();
    }

    @GetMapping("/user/info/{userId}")
    @ResponseBody
    public R userInfo(@PathVariable("userId") Integer userId){
        SysUserVo_Add sysUserVo_add = sysUserVo_addService.findByUserId(userId);
        return R.ok().put("user", sysUserVo_add);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public R userUpdate(@RequestBody SysUserVo_Add sysUserVo_add){
        sysUserVo_addService.update(sysUserVo_add);
        return R.ok();
    }

    @PostMapping("/user/del/{userId}")
    @ResponseBody
    public R userDel(@PathVariable("userId") Integer userId) {
        try {
            //删除用户角色
            roleService.deleteByUserId(userId);
            //删除用户
            sysUserService.deleteByUserId(userId);
            return R.ok();
        } catch (Exception e) {
            return R.error("删除失败");
        }
    }
}
