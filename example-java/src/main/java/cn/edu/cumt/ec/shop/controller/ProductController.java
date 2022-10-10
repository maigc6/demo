package cn.edu.cumt.ec.shop.controller;

import cn.edu.cumt.ec.shop.dto.ProductQueryParam;
import cn.edu.cumt.ec.shop.service.IProductService;
import cn.edu.cumt.ec.shop.vo.ProductVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private IProductService productService;

    @PostMapping(value = "/app/page")
    @ApiOperation(value = "【查询】通过条件分页查询APP商品列表", notes = "通过条件查询APP商品列表", response = ProductVo.class)
    public Page<ProductVo> appPage(@Validated @RequestBody ProductQueryParam productQueryParam){
        return productService.appPage(productQueryParam);
    }

    @PostMapping(value = "/app/detail")
    @ApiOperation(value = "根据id查询商品详情信息")
    public ProductVo detail(@RequestParam Long productId){
        return productService.detail(productId);
    }
}
