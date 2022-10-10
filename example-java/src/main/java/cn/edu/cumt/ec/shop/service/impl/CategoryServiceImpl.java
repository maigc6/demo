package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.entity.Category;
import cn.edu.cumt.ec.shop.mapper.CategoryMapper;
import cn.edu.cumt.ec.shop.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品类别表 服务实现类
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
