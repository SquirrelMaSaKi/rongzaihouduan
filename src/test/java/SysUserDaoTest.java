import com.rj.dao.SysUserDao;
import com.rj.dao.SysUserVo_AddDao;
import com.rj.pojo.SysUser;
import com.rj.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class SysUserDaoTest {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserVo_AddDao sysUserVo_addDao;


    @Test
    public void testFindByUsername() {
        SysUser sysUser = sysUserDao.findByUsername("admin");
        System.out.println(sysUser);
    }

    @Test
    public void testAddSysUser(){
        SysUser sysUser = new SysUser(null, "rj", "123", "123@163.com", "13343543322", 1, 1, new Date(), 1, "男", null);
        sysUserService.add(sysUser);
    }

}
