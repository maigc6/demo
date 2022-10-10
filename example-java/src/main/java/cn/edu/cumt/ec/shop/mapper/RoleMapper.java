package cn.edu.cumt.ec.shop.mapper;

import cn.edu.cumt.ec.shop.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> listByUserId(@Param("userId") Long userId);
}
