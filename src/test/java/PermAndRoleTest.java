import com.rj.service.PermService;
import com.rj.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class PermAndRoleTest {
    @Autowired
    private PermService permService;
    @Autowired
    private RoleService roleService;

    @Test
    public void testFind(){
        Set<String> roles = roleService.roles(1);
        for (String role : roles) {
            System.out.println(role);
        }

        System.out.println("=================");

        Set<String> perms = permService.perms(1);
        for (String perm : perms) {
            System.out.println(perm);
        }
    }
}
