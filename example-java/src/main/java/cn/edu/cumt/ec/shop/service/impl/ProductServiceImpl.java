package cn.edu.cumt.ec.shop.service.impl;

import cn.edu.cumt.ec.shop.dto.ProductQueryParam;
import cn.edu.cumt.ec.shop.entity.Product;
import cn.edu.cumt.ec.shop.mapper.ProductMapper;
import cn.edu.cumt.ec.shop.service.IProductService;
import cn.edu.cumt.ec.shop.vo.ProductVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Resource
    ProductMapper shopProductMapper;
    @Override
    public Page<ProductVo> appPage(ProductQueryParam shopProductQueryParam) {
        return shopProductMapper.appPage(new Page(shopProductQueryParam.getCurrent(), shopProductQueryParam.getSize()),shopProductQueryParam);
    }
    @Override
    public ProductVo detail(Long productId) {
        return shopProductMapper.detail(productId);
    }
}
