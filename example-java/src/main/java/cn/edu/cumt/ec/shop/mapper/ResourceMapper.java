package cn.edu.cumt.ec.shop.mapper;

import cn.edu.cumt.ec.shop.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    List<Resource> getByUserid(@Param("userId") Long userId);
}
