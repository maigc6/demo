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
@ApiModel(value="UserQueryParam", description="用户查询参数")
public class UserQueryParam extends PageUtil implements Serializable {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("角色id")
    private Long roleId;

}
