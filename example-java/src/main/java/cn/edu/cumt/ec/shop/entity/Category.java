package cn.edu.cumt.ec.shop.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品类别表
 * </p>
 *
 * @author 孟现飞
 * @since 2021-12-16
 */
@Getter
@Setter
@ApiModel(value = "Category对象", description = "商品类别表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("category_id")
    @ApiModelProperty("类别id")
    private Long categoryId;

    @ApiModelProperty("类别名称")
    private String categoryName;

    @ApiModelProperty("显示顺序")
    private Integer showSequence;

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
