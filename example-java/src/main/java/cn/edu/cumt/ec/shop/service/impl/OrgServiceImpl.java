package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.entity.Org;
import cn.edu.cumt.ec.shop.mapper.OrgMapper;
import cn.edu.cumt.ec.shop.service.IOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2021-11-03
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

}
