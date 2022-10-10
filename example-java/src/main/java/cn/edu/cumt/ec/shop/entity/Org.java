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
 * 部门表
 * </p>
 *
 * @author baomidou
 * @since 2022-05-06
 */
@Data
@ApiModel(value = "Org对象", description = "部门表")
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门id")
    @TableId(value = "org_id", type = IdType.AUTO)
    private Long orgId;

    @ApiModelProperty("部门编码，以上级部门编码开头加上-")
    private String orgSn;

    @ApiModelProperty("部门编码名称")
    private String orgName;

    @ApiModelProperty("父级部门编号")
    private String parentOrgSn;

    @ApiModelProperty("末级，0：不是，1：是")
    private Integer leaf;

    @ApiModelProperty("简称")
    private String abbreviation;

    @ApiModelProperty("主管id")
    private Long directorId;

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
    private Integer deleted;


}
