package cn.edu.cumt.ec.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// @SpringBootApplication指定这是一个 spring boot的应用程序.
@SpringBootApplication
// 扫描数据访问层接口的包名。
@MapperScan("cn.edu.cumt.ec.shop.mapper")
public class ShopApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        // SpringApplication 用于从main方法启动Spring应用的类。
        SpringApplication.run(ShopApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShopApplication.class);
    }
}
