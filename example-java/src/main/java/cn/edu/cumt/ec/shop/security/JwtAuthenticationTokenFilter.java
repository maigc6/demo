package cn.edu.cumt.ec.shop.security;

import cn.edu.cumt.ec.shop.service.IUserService;
import cn.edu.cumt.ec.shop.util.JwtUtils;
import cn.edu.cumt.ec.shop.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token校验
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String token_header;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private IUserService userService;

    @Resource
    RedisUtil redisUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("/doc.html") ||
               path.contains("/swagger-ui.html") ||
               path.contains("/swagger-ui") ||
               path.contains("/swagger-resources") ||
               path.contains("/v2/api-docs") ||
               path.contains("/v3/api-docs") ||
               path.contains("/webjars")||
               path.contains("/favicon.ico");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String auth_token = request.getHeader(this.token_header);
        final String auth_token_start = "Bearer ";
        if (StringUtils.isNotEmpty(auth_token) && auth_token.startsWith(auth_token_start)) {
            auth_token = auth_token.substring(auth_token_start.length());
            //验证token签名是否有效
            if (!jwtUtils.validateSignature(auth_token)) {
                auth_token = null;// 签名无效,不允许通过验证
            }
        } else {
            auth_token = null;// 不按规范,不允许通过验证
        }

        String phoneNumber = jwtUtils.getPhoneNumberFromToken(auth_token);

        logger.info(String.format("Checking authentication for userDetail %s.", phoneNumber));

        if (jwtUtils.containToken(phoneNumber, auth_token) && phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtUser jwtUser = (JwtUser) redisUtil.get(phoneNumber);
            if (jwtUser == null) {
                //根据手机号从数据库里取出用户的信息
                jwtUser = (JwtUser) userService.loadUserByUsername(phoneNumber);
                if (jwtUser != null) {
                    redisUtil.set(phoneNumber, jwtUser);
                }
            }
            if (jwtUtils.validateDatetime(auth_token, jwtUser)) {//验证token是否过期或在用户信息更新之前
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info(String.format("Authenticated userDetail %s, setting security context", phoneNumber));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                redisUtil.del(phoneNumber);
            }
        }
        chain.doFilter(request, response);
    }
}
