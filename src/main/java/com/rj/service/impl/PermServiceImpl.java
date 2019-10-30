package com.rj.service.impl;

import com.rj.dao.PermDao;
import com.rj.service.PermService;
import com.rj.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("permService")
public class PermServiceImpl implements PermService {
    @Autowired
    private PermDao permDao;

    @Override
    public Set<String> perms(Integer userId) {
        List<String> newPerms = new ArrayList<>();
        List<String> perms = permDao.perms(userId);
        for (String perm : perms) {
            if(!StringUtil.isEmpty(perm)){
                if(perm.contains(",")){
                    String[] strings = perm.split(",");
                    for (String string : strings) {
                        newPerms.add(string);
                    }
                } else {
                    newPerms.add(perm);
                }
            }
        }
        return new HashSet<String>(newPerms);
    }
}
