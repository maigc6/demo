package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.exception.CustomException;
import cn.edu.cumt.ec.shop.exception.ErrorCode;
import cn.edu.cumt.ec.shop.mapper.RoleMapper;
import cn.edu.cumt.ec.shop.mapper.UserMapper;
import cn.edu.cumt.ec.shop.mapper.UserRoleMapper;
import cn.edu.cumt.ec.shop.security.JwtUser;
import cn.edu.cumt.ec.shop.security.ResponseUserToken;
import cn.edu.cumt.ec.shop.util.JwtUtils;
import cn.edu.cumt.ec.shop.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AuthServiceImpl implements cn.edu.cumt.ec.shop.service.AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Resource
    private RedisUtil redisUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, @Qualifier("userService") UserDetailsService userDetailsService, JwtUtils jwtTokenUtil, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    @Override
    public User register(User user) {
        final String username = user.getUserName();
        if(userMapper.selectOne(new QueryWrapper<User>().eq("phone_number",user.getPhoneNumber()))!=null) {
            throw new CustomException(ErrorCode.UNKNOW_ERROR);//用户名已经存在
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPwd();
        user.setPwd(encoder.encode(rawPassword));
        userMapper.insert(user);
        return user;
    }

    @Override
    public ResponseUserToken login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        final String token = jwtUtils.generateAccessToken(jwtUser);
        //存储token
        jwtUtils.putToken(username, token);
        //将用户信息写入redis
        redisUtil.set(jwtUser.getPhoneNumber(),jwtUser);

        return new ResponseUserToken(token);
    }

    @Override
    public void logout(String token) {
        token = token.substring(tokenHead.length());
        String userName = jwtUtils.getUsernameFromToken(token);
        jwtUtils.deleteToken(userName);
    }

    @Override
    public ResponseUserToken refresh(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        String username = jwtUtils.getUsernameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtUtils.canTokenBeRefreshed(token, jwtUser.getUpdatedDatetime())){
            token =  jwtUtils.refreshToken(token);
            return new ResponseUserToken(token);
        }
        return null;
    }

    @Override
    public JwtUser getUserByToken(String token) {
        token = token.substring(tokenHead.length());
        return jwtUtils.getUserFromToken(token);
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ErrorCode.UNKNOW_ERROR);//登录失败
        }
    }
}
