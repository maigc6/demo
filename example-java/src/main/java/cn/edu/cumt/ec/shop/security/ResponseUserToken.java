package cn.edu.cumt.ec.shop.security;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
}
