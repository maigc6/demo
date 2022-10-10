package cn.edu.cumt.ec.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author baomidou
 * @since 2022-05-06
 */
@Data
@TableName("role_resource")
@ApiModel(value = "RoleResource对象", description = "角色资源表")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色资源id")
    @TableId(value = "role_resource_id", type = IdType.AUTO)
    private Long roleResourceId;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("资源id")
    private Long resourceId;


}
