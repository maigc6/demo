package cn.edu.cumt.ec.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-05-06
 */
@Data
@TableName("user_org")
@ApiModel(value = "UserOrg对象", description = "")
public class UserOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "user_org_id", type = IdType.AUTO)
    private Long userOrgId;

    private Long userId;

    private String orgSn;

    @ApiModelProperty("入职时间")
    private LocalDateTime entryDatetime;

    @ApiModelProperty("在职，0：离职，1：在职")
    private Integer onJob;

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

    @ApiModelProperty("启用，0：禁用，1：启用")
    private Integer enabled;


}
