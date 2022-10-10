package cn.edu.cumt.ec.shop.vo.mapper;

import cn.edu.cumt.ec.shop.entity.Category;
import cn.edu.cumt.ec.shop.vo.CategoryVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-10T21:45:35+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_333 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryVo toCateogryVo(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryVo categoryVo = new CategoryVo();

        categoryVo.setCategoryId( category.getCategoryId() );
        categoryVo.setCategoryName( category.getCategoryName() );
        categoryVo.setShowSequence( category.getShowSequence() );

        return categoryVo;
    }

    @Override
    public List<CategoryVo> toCateogryVo(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryVo> list = new ArrayList<CategoryVo>( categories.size() );
        for ( Category category : categories ) {
            list.add( toCateogryVo( category ) );
        }

        return list;
    }
}
