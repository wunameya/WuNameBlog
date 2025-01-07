import org.eu.wuname.domain.entity.User;
import org.eu.wuname.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {



    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        User user = userMapper.getUser(1);
        System.out.println(user.toString());
    }
}
