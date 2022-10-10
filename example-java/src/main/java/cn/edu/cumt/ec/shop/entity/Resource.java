package cn.edu.cumt.ec.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author baomidou
 * @since 2022-05-06
 */
@Data
@ApiModel(value = "Resource对象", description = "资源表")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源id")
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("资源类型，1：菜单，2：按钮")
    private String resourceType;

    @ApiModelProperty("父级资源id")
    private Long parentResourceId;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("叶子，0：不是，1：是")
    private Integer leaf;

    @ApiModelProperty("显示顺序")
    private Integer showSequence;

    @ApiModelProperty("创建人id")
    private Long creatorId;

    @ApiModelProperty("创建人姓名")
    private String creatorName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdDatetime;

    @ApiModelProperty("修改人id")
    private Long modifierId;

    @ApiModelProperty("修改人姓名")
    private String modifierName;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedDatetime;

    @ApiModelProperty("删除状态")
    private Boolean deleted;


}
