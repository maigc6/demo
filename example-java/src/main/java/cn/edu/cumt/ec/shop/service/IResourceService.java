package cn.edu.cumt.ec.shop.service;

import cn.edu.cumt.ec.shop.entity.Resource;
import cn.edu.cumt.ec.shop.vo.ResourceVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 将List<Resource>转换为List<ResourceVo>
     * @param resources
     * @return
     */
    List<ResourceVo> convert(List<Resource> resources);

    /**
     * 将Resource转换为ResourceVo
     * @param resources
     * @param resourceVo
     * @return
     */
    ResourceVo convert(List<Resource> resources, ResourceVo resourceVo);
}
