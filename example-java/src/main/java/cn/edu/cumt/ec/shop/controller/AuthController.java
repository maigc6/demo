package cn.edu.cumt.ec.shop.controller;

import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.exception.CustomException;
import cn.edu.cumt.ec.shop.exception.ErrorCode;
import cn.edu.cumt.ec.shop.dto.UserLoginDTO;
import cn.edu.cumt.ec.shop.security.JwtUser;
import cn.edu.cumt.ec.shop.security.ResponseUserToken;
import cn.edu.cumt.ec.shop.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@Api(value = "登陆注册及刷新token")
@RequestMapping("/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResponseUserToken login(
            @Valid @RequestBody UserLoginDTO userLoginParam) {
        final ResponseUserToken responseUserToken = authService.login(userLoginParam.getPhoneNumber(), userLoginParam.getPwd());
        return responseUserToken;
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public String logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);//未授权
        }
        authService.logout(token);
        return "logout success";
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public JwtUser getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);//未授权
        }
        JwtUser jwtUser = authService.getUserByToken(token);
        return jwtUser;
    }

    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册")
    public User sign(@RequestBody User user) {
        if (StringUtils.isAnyBlank(user.getPhoneNumber(), user.getPwd())) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);//
        }
        return authService.register(user);
    }
}