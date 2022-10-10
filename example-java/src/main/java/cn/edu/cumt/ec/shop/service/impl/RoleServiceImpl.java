package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.entity.Role;
import cn.edu.cumt.ec.shop.mapper.RoleMapper;
import cn.edu.cumt.ec.shop.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
