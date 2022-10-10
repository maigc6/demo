package cn.edu.cumt.ec.shop.vo.mapper;

import cn.edu.cumt.ec.shop.entity.Category;
import cn.edu.cumt.ec.shop.vo.CategoryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class);
    CategoryVo toCateogryVo(Category category);
    List<CategoryVo> toCateogryVo(List<Category> categories);
}
