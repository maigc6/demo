package cn.edu.cumt.ec.shop.service;

import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.security.JwtUser;
import cn.edu.cumt.ec.shop.security.ResponseUserToken;

/**
 * @author: JoeTao
 * createAt: 2018/9/17
 */
public interface AuthService {

    User register(User jwtUser);

    ResponseUserToken login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    JwtUser getUserByToken(String token);
}
