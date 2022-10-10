package cn.edu.cumt.ec.shop.vo;

import cn.edu.cumt.ec.shop.entity.CosFile;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
*
* @author gy
* @since 2021-08-31
*/
@Data
@ApiModel(value="ProductVo视图", description="商品视图")
public class ProductVo implements Serializable {

    @TableId("product_id")
    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品类别")
    private Long categoryId;

    @ApiModelProperty(value = "类别名称")
    private String categoryName;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品简介")
    private String introduction;

    @ApiModelProperty(value = "价格")
    private Integer price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "销量")
    private int saleCount;

    @ApiModelProperty(value = "上驾状态 0:下架 1:上架")
    private Integer onShelf;

    @ApiModelProperty(value = "商品图片地址")
    private List<CosFile> imageUrls;

}
