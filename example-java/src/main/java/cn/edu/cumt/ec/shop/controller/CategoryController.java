package cn.edu.cumt.ec.shop.controller;


import cn.edu.cumt.ec.shop.entity.Category;
import cn.edu.cumt.ec.shop.service.ICategoryService;
import cn.edu.cumt.ec.shop.vo.CategoryVo;
import cn.edu.cumt.ec.shop.vo.mapper.CategoryMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品类别表 前端控制器
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Resource
    private ICategoryService categoryService;

    @PostMapping(value = "/vo/list")
    @ApiOperation(value = "【查询】通过条件分页查询商品类别列表", notes = "通过条件查询商品类别列表", response = CategoryVo.class)
    public List<CategoryVo> list(){
        return CategoryMapper.INSTANCE.toCateogryVo(categoryService.list(null));
    }

    @PostMapping(value = "/id")
    @ApiOperation(value = "根据id查询商品类别基本信息")
    public Category getById(@RequestParam Long categoryId){
        return categoryService.getById(categoryId);
    }

}
