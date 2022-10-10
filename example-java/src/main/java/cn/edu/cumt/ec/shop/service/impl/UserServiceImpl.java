package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.dto.UserAddEditParam;
import cn.edu.cumt.ec.shop.dto.UserQueryParam;
import cn.edu.cumt.ec.shop.entity.Resource;
import cn.edu.cumt.ec.shop.entity.Role;
import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.entity.UserRole;
import cn.edu.cumt.ec.shop.mapper.ResourceMapper;
import cn.edu.cumt.ec.shop.mapper.RoleMapper;
import cn.edu.cumt.ec.shop.mapper.UserMapper;
import cn.edu.cumt.ec.shop.security.JwtUser;
import cn.edu.cumt.ec.shop.service.IResourceService;
import cn.edu.cumt.ec.shop.service.IUserRoleService;
import cn.edu.cumt.ec.shop.service.IUserService;
import cn.edu.cumt.ec.shop.util.JwtUtils;
import cn.edu.cumt.ec.shop.vo.ResourceVo;
import cn.edu.cumt.ec.shop.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    IResourceService resourceService;

    @Autowired
    IUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser jwtUser = userMapper.getJwtUserByPhoneNumber(username);
        if (jwtUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 创建List集合，用来保存用户权限，GrantedAuthority对象代表赋予给当前用户的权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 获得当前用户权限集合
        List<Role> roles=roleMapper.listByUserId(jwtUser.getUserId());
        for (Role role : roles) {
            // 将关联对象Role的authority属性保存为用户的认证权限
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        jwtUser.setAuthorities(authorities);
        return jwtUser;
    }

    /**
     * 分页查询
     * @param userQueryParam
     * @return
     */
    public Page<UserVo> page(UserQueryParam userQueryParam) {
        return userMapper.page(new Page<UserVo>(userQueryParam.getCurrent(), userQueryParam.getSize()), userQueryParam);
    }

    @Transactional
    @Override
    public Boolean add(UserAddEditParam userAddEditParam) {
        JwtUser jwtUser= JwtUtils.getJwtUser();
        //添加用户
        User user=new User();
        BeanUtils.copyProperties(userAddEditParam,user);
        user.setUserId(null);
        user.setCreatorId(jwtUser.getUserId());
        user.setCreatorName(jwtUser.getUsername());
        save(user);
        //保存角色
        for(Long roleId : userAddEditParam.getRoleIds()){
            UserRole userRole=new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            userRole.setCreatorId(jwtUser.getUserId());
            userRole.setCreatorName(jwtUser.getUsername());
            userRoleService.save(userRole);
        }
        return true;
    }

    @Override
    public Boolean update(UserAddEditParam userAddEditParam) {
        JwtUser jwtUser= JwtUtils.getJwtUser();
        //更新用户
        User currentUser=getById(userAddEditParam.getUserId());
        BeanUtils.copyProperties(userAddEditParam,currentUser);
        currentUser.setModifierId(jwtUser.getUserId());
        currentUser.setModifierName(jwtUser.getUsername());
        updateById(currentUser);
        //删除现有的角色
        userRoleService.remove( new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,userAddEditParam.getUserId()));
        //保存角色
        for(Long roleId : userAddEditParam.getRoleIds()){
            UserRole userRole=new UserRole();
            userRole.setUserId(currentUser.getUserId());
            userRole.setRoleId(roleId);
            userRole.setCreatorId(jwtUser.getUserId());
            userRole.setCreatorName(jwtUser.getUsername());
            userRoleService.save(userRole);
        }
        return true;
    }

    @Override
    public List<ResourceVo> resources() {
        JwtUser jwtUser= JwtUtils.getJwtUser();
        //获取用户的所有资源
        List<Resource> resources=resourceMapper.getByUserid(jwtUser.getUserId());
        //根据 List<Resource>构造List<ResourceVo>
        return resourceService.convert(resources);
    }

}
