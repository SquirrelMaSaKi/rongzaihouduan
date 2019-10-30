package com.rj.vo;

import com.rj.pojo.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Role_MenuVo extends Role {
    private List<Integer> menus;
}
