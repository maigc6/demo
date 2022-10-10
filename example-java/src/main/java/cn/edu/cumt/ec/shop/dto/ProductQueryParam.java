package cn.edu.cumt.ec.shop.dto;

import cn.edu.cumt.ec.shop.util.PageUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
*
* @author gy
* @since 2021-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductQueryParam", description="商品查询参数")
public class ProductQueryParam extends PageUtil implements Serializable {

    @ApiModelProperty(value = "商品类别")
    private Long categoryId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "上驾状态")
    private Integer onShelf;

}
