package cn.edu.cumt.ec.shop.service;

import cn.edu.cumt.ec.shop.dto.ProductQueryParam;
import cn.edu.cumt.ec.shop.entity.Product;
import cn.edu.cumt.ec.shop.vo.ProductVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
public interface IProductService extends IService<Product> {
    /**
     * 查询app商品列表
     * @param shopProductQueryParam
     * @return
     */
    Page<ProductVo> appPage(ProductQueryParam shopProductQueryParam);

    /**
     * 查询商品详情信息
     * @param productId
     * @return
     */
    ProductVo detail(Long productId);
}
