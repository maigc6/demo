package cn.edu.cumt.ec.shop.util;

import cn.edu.cumt.ec.shop.security.JwtUser;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.noggit.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: JoeTao
 * createAt: 2018/9/14
 */
@Slf4j
@Component
public class JwtUtils {
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";
    private static final String CLAIM_KEY_USERNAME = "userName";
    private static final String CLAIM_KEY_PHONE_NUMBER = "phoneNumber";
    private static final String CLAIM_KEY_EMAIL = "email";
    private static final String CLAIM_KEY_ORG_SN = "orgSn";
    private static final String CLAIM_KEY_UPDATED_DATETIME = "updatedDatetime";
    private static final String CLAIM_KEY_ENABLED = "enabled";
    private static final String CLAIM_KEY_DELETED = "deleted";


    private Map<String, String> tokenMap = new ConcurrentHashMap<>(32);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long access_token_expiration;

    @Value("${jwt.expiration}")
    private Long refresh_token_expiration;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public JwtUser getUserFromToken(String token) {
        JwtUser jwtUser = new JwtUser();
        try {
            final Claims claims = getClaimsFromToken(token);
            Long userId = getUserIdFromToken(token);
            String username = claims.getSubject();
            String roleName = claims.get(CLAIM_KEY_AUTHORITIES).toString();
            String phoneNumber = claims.get(CLAIM_KEY_PHONE_NUMBER).toString();
            jwtUser.setUserId(userId);
            jwtUser.setPhoneNumber(phoneNumber);
            if (claims.get(CLAIM_KEY_USERNAME) != null) {
                jwtUser.setUserName(claims.get(CLAIM_KEY_USERNAME).toString());
            }
            if (claims.get(CLAIM_KEY_ORG_SN) != null) {
                jwtUser.setOrgSn(claims.get(CLAIM_KEY_ORG_SN).toString());
            }
            if (claims.get(CLAIM_KEY_EMAIL) != null) {
                jwtUser.setEmail(claims.get(CLAIM_KEY_EMAIL).toString());
            }
            if (claims.get(CLAIM_KEY_UPDATED_DATETIME) != null) {
                jwtUser.setUpdatedDatetime(new Date((Long)(claims.get(CLAIM_KEY_UPDATED_DATETIME))));
            }
            if (claims.get(CLAIM_KEY_ENABLED) != null) {
                jwtUser.setEnabled(Integer.parseInt(claims.get(CLAIM_KEY_ENABLED).toString()));
            }
            if (claims.get(CLAIM_KEY_DELETED) != null) {
                jwtUser.setDeleted(Integer.parseInt(claims.get(CLAIM_KEY_DELETED).toString()));
            }
        } catch (Exception e) {
            jwtUser = null;
        }
        return jwtUser;
    }

    public Long getUserIdFromToken(String token) {
        Long userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = Long.parseLong(String.valueOf(claims.get(CLAIM_KEY_USER_ID)));
        } catch (Exception e) {
            userId = 0L;
        }
        return userId;
    }

    public String getPhoneNumberFromToken(String token) {
        String phoneNumber;
        try {
            final Claims claims = getClaimsFromToken(token);
            phoneNumber = claims.get(CLAIM_KEY_PHONE_NUMBER).toString();
        } catch (Exception e) {
            phoneNumber = null;
        }
        return phoneNumber;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public String generateAccessToken(JwtUser jwtUser) {
        Map<String, Object> claims = generateClaims(jwtUser);
        claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(jwtUser.getAuthorities()).get(0));
        return generateAccessToken(jwtUser.getUsername(), claims);
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateSignature(String token) {
        try {
            //验证token的签名是否有效，如果抛出异常，则无效
            Jws jws = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(token);
        }catch (SignatureException signatureException){
            return false;
        }
        return true;
    }

    public Boolean validateDatetime(String token, UserDetails userDetails) {
        JwtUser jwtUser = (JwtUser) userDetails;
        final Date created = getCreatedDateFromToken(token);
        return (!isTokenExpired(token)&&
                !isCreatedBeforeLastPasswordReset(created, jwtUser.getUpdatedDatetime()));
    }

    public String generateRefreshToken(JwtUser jwtUser) {
        Map<String, Object> claims = generateClaims(jwtUser);
        // 只授于更新 token 的权限
        String roles[] = new String[]{JwtUtils.ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JSONUtil.toJSON(roles));
        return generateRefreshToken(jwtUser.getUsername(), claims);
    }

    public void putToken(String userName, String token) {
        tokenMap.put(userName, token);
    }

    public void deleteToken(String userName) {
        tokenMap.remove(userName);
    }

    public boolean containToken(String userName, String token) {
        if (userName != null && tokenMap.containsKey(userName) && tokenMap.get(userName).equals(token)) {
            return true;
        }
        return false;
    }
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Map<String, Object> generateClaims(JwtUser jwtUser) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USER_ID, jwtUser.getUserId());
        claims.put(CLAIM_KEY_USERNAME, jwtUser.getUsername());
        claims.put(CLAIM_KEY_PHONE_NUMBER, jwtUser.getPhoneNumber());
        claims.put(CLAIM_KEY_EMAIL, jwtUser.getEmail());
        claims.put(CLAIM_KEY_ORG_SN, jwtUser.getOrgSn());
        claims.put(CLAIM_KEY_UPDATED_DATETIME, jwtUser.getUpdatedDatetime());
        claims.put(CLAIM_KEY_ENABLED, jwtUser.getEnabled());
        claims.put(CLAIM_KEY_DELETED, jwtUser.getDeleted());
        return claims;
    }

    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, access_token_expiration);
    }

    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }


    private String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, refresh_token_expiration);
    }



    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    public static JwtUser getJwtUser() {
        JwtUser jwtUser=null;
        try {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            jwtUser = (JwtUser) obj;
        } catch (Exception ex) {
            log.warn("从SpringSecurity中获取用户信息失败，请确认请求头是否包含token!");
        }
        return jwtUser;
    }
}
