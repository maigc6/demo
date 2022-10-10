package cn.edu.cumt.ec.shop.security;

import cn.edu.cumt.ec.shop.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class JwtUser implements UserDetails {
    private Long userId;
    private String userName;
    private String phoneNumber;
    private String password;
    private String email;
    private String orgSn;
    private Role role;
    private List<GrantedAuthority> authorities;
    private Date updatedDatetime;
    private Integer enabled;
    private Integer deleted;

    public JwtUser() {
    }

    public JwtUser(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public JwtUser(Long userId, String userName, String phoneNumber,
                   String password, String email, String orgSn,
                   Date updatedDatetime, Integer enablded, Integer deleted) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.orgSn = orgSn;
        this.updatedDatetime = updatedDatetime;
        this.enabled = enablded;
        this.deleted = deleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *  账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }
}
