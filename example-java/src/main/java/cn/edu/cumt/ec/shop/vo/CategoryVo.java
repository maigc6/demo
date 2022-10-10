package cn.edu.cumt.ec.shop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* @author 孟现飞
* @since 2021-12-16
*/
@Data
@ApiModel(value="CategoryVo视图", description="商品类别Vo")
public class CategoryVo implements Serializable {

    @ApiModelProperty(value = "类别id")
    private Long categoryId;

    @ApiModelProperty(value = "类别名称")
    private String categoryName;

    @ApiModelProperty(value = "显示顺序")
    private Integer showSequence;

}
