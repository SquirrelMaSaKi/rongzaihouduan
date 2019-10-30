import com.rj.dao.MenuDao;
import com.rj.service.MenuService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class SysMenuTest {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuService menuService;


}
