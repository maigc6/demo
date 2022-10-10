package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.entity.Resource;
import cn.edu.cumt.ec.shop.mapper.ResourceMapper;
import cn.edu.cumt.ec.shop.service.IResourceService;
import cn.edu.cumt.ec.shop.vo.ResourceVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
    @Override
    public List<ResourceVo> convert(List<Resource> resources) {
        //获取顶级资源项
        List<ResourceVo> resourceVos= resources.stream().filter(resource -> resource.getParentResourceId()==null
        ).map(item->{
            ResourceVo topResourceVo=new ResourceVo();
            BeanUtils.copyProperties(item,topResourceVo);
            topResourceVo.setChildren(new ArrayList<ResourceVo>());
            return topResourceVo;
        }).collect(Collectors.toList());

        for(ResourceVo resourceVo:resourceVos){
            convert(resources,  resourceVo);
        }
        return resourceVos;
    }

    @Override
    public ResourceVo convert(List<Resource> resources, ResourceVo resourceVo) {
        //获取resource的子项
        if(resources.stream().anyMatch(item->item.getParentResourceId()==resourceVo.getResourceId())){
            resourceVo.getChildren().addAll(
                    resources.stream().filter(item -> item.getParentResourceId()==resourceVo.getResourceId())
                            .map(item->{
                                ResourceVo childResourceVo=new ResourceVo();
                                BeanUtils.copyProperties(item,childResourceVo);
                                return childResourceVo;
                            }).collect(Collectors.toList())
            );
            for (ResourceVo r : resourceVo.getChildren()) {
                convert(resources, r);
            }
        }
        return resourceVo;
    }
}
