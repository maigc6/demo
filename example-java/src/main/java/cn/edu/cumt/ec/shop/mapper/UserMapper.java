package cn.edu.cumt.ec.shop.mapper;

import cn.edu.cumt.ec.shop.dto.UserQueryParam;
import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.security.JwtUser;
import cn.edu.cumt.ec.shop.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
public interface UserMapper extends BaseMapper<User> {
   JwtUser getJwtUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

   Page<UserVo> page(IPage<UserVo> page, UserQueryParam userQueryParam);
}
