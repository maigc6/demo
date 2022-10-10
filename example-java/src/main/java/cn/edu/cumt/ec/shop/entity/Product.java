package cn.edu.cumt.ec.shop.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
@Getter
@Setter
@ApiModel(value = "Product对象", description = "商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品类别")
    private Long categoryId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品简介")
    private String introduction;

    @ApiModelProperty("积分")
    private Integer price;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("销量")
    private Integer saleCount;

    @ApiModelProperty("上驾，0：下架，1：上架")
    private Integer onShelf;

    @ApiModelProperty("创建人id")
    private Long creatorId;

    @ApiModelProperty("创建人姓名")
    private String creatorName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdDatetime;

    @ApiModelProperty("修改人id")
    private Long editorId;

    @ApiModelProperty("修改人姓名")
    private String editorName;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedDatetime;

    @ApiModelProperty("删除状态")
    private Integer deleted;


}
