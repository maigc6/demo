package cn.edu.cumt.ec.shop.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/shop?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("孟现飞") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride()// 覆盖已生成文件
                            .outputDir("D://new"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.edu.cumt.ec.demo") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://new")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("category", "product") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .mapperBuilder()
                            .enableBaseResultMap() //mapper.xml文件中生成：BaseResultMap
                            .enableBaseColumnList()
                            .entityBuilder().enableLombok().idType(IdType.AUTO)
                            .controllerBuilder().enableRestStyle(); //mapper.xml文件中生成：通用查询结果列<sql id="Base_Column_List">
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
