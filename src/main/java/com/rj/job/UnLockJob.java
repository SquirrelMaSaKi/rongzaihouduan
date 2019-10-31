package com.rj.job;


import com.rj.pojo.SysUser;
import com.rj.service.SysUserService;
import com.rj.utils.ApplicationContextUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Setter
public class UnLockJob implements Job{
    //private Logger log = LoggerFactory.getLogger(UnLockJob.class);
    //private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UnLockJob.class);
    /**
     * 检查用户是否需要解锁
     * @param context
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysUserService userService = (SysUserService) ApplicationContextUtil.getBean("sysUserService");
        List<SysUser> users = userService.querySysUsers();
        for (SysUser user : users) {
            log.debug("用户状态:"+user.getStatus());
            System.out.println("用户状态:"+user.getStatus());
            if(user.getStatus()==0){
                log.debug("用户:"+user.getUsername()+" 被发现了是锁定状态");
                // 获取当天 日期
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int i = calendar.get(Calendar.MINUTE);
                // 获取锁定 日期
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(user.getLockdate());
                int j = calendar2.get(Calendar.MINUTE);
                if(i>j){//锁定期到，解锁
                    log.debug("用户:"+user.getUsername()+" 锁定到期");
                    userService.unlockUser(user.getUserId());
                }
            }
        }
    }
}
