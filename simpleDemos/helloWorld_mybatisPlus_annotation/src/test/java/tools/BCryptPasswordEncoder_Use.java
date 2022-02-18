package tools;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoder_Use {
    @Test
    public void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //每次加密的结果都不同
        System.out.println(encoder.encode("helloWorld"));
        System.out.println(encoder.encode("helloWorld"));
        //但是验证都会成功
        System.out.println(encoder.matches("helloWorld","$2a$10$ArlME3GOohxqNH5oMRN6yuCAY1ybaEf/S8e7IZNry.jWN.yvvdj2i"));
        System.out.println(encoder.matches("helloWorld","$2a$10$MyEAPeEpCk7KvgF1.G/FsOfuxZQIdjLaqdOZckfiPWbdMpZWhH7g."));
    }
    @Test void createPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("lucia"));
        System.out.println(encoder.encode("userA"));
    }
}
