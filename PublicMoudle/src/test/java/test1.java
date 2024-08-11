import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class test1 {

    //把官方的PasswordEncoder密码加密方式替换成BCryptPasswordEncoder
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Test
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);

    }
}
