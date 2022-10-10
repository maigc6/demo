package cn.edu.cumt.ec.shop.service;

import cn.edu.cumt.ec.shop.dto.UserAddEditParam;
import cn.edu.cumt.ec.shop.dto.UserQueryParam;
import cn.edu.cumt.ec.shop.entity.User;
import cn.edu.cumt.ec.shop.vo.ResourceVo;
import cn.edu.cumt.ec.shop.vo.UserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
public interface IUserService extends IService<User>,UserDetailsService {
    Page<UserVo> page(UserQueryParam userQueryParam);

    /**
     * 添加用户
     * @param userAddEditParam
     * @return
     */
    Boolean add(UserAddEditParam userAddEditParam);

    /**
     * 更新用户
     * @param userAddEditParam
     * @return
     */
    Boolean update(UserAddEditParam userAddEditParam);

    /**
     * 获取当前用户的资源项
     * @return
     */
    List<ResourceVo> resources();
}
