package cn.edu.cumt.ec.shop;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger= LoggerFactory.getLogger(getClass());

    @Test
    public void test(){
        redisTemplate.boundValueOps("StringKey").set("StringValue");
    }
    @Test
    public void delete(){
        redisTemplate.delete("StringKey");
    }
    @Test
    public void expire(){
        redisTemplate.expire("StringKey",20,TimeUnit.SECONDS);
    }
    @Test
    public void hasKey(){

        logger.info(redisTemplate.hasKey("StringKey").toString());
    }
}
