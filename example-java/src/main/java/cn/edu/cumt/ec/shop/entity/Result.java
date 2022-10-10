package cn.edu.cumt.ec.shop.entity;

import lombok.Data;

/**
 * 统一返回的数据格式
 * @param <T>
 */
@Data
public class Result<T> {
    private Integer code; // 状态码
    private String message; // 状态描述信息
    private T data; // 定义为范型
}
