import com.rj.dao.MenuDao;
import com.rj.pojo.Menu;
import com.rj.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class SysMenuTest {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuService menuService;

    @Test
    public void testFindMenu2() {
        List<Menu> menuList = menuDao.findBySysUserId2(1);
        System.out.println(menuList);
        for (Menu menu : menuList) {
            for (Menu menu1 : menu.getList()) {
                System.out.println(menu1);
            }
        }
    }

    @Test
    public void testFindMenu() {
        List<Menu> menuList = menuService.findBySysUserId(1);
        System.out.println(menuList);
        for (Menu menu : menuList) {
            List<Menu> list = menu.getList();
            for (Menu menu1 : list) {
                System.out.println(menu1);
            }
        }
    }

}
