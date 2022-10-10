package cn.edu.cumt.ec.shop;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AppTest
{
    Logger logger= LoggerFactory.getLogger(AppTest.class);
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testCreateBCrypt(){
        logger.info(passwordEncoder.encode("123456"));
    }
}
