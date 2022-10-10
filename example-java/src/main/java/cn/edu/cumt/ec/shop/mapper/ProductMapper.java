package cn.edu.cumt.ec.shop.mapper;

import cn.edu.cumt.ec.shop.dto.ProductQueryParam;
import cn.edu.cumt.ec.shop.entity.Product;
import cn.edu.cumt.ec.shop.vo.ProductVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 查询app商品列表
     * @param shopProductQueryParam
     * @return
     */
    Page<ProductVo> appPage(Page page, @Param("queryParam") ProductQueryParam shopProductQueryParam);

    /**
     * 查询商品详情信息
     * @param productId
     * @return
     */
    ProductVo detail(Long productId);
}
