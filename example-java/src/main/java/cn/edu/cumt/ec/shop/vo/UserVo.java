package cn.edu.cumt.ec.shop.vo;

import cn.edu.cumt.ec.shop.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVo {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String pwd;

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

    @ApiModelProperty("删除标志，0：表示未删除，1：表示删除")
    private Integer deleted;

    @ApiModelProperty("角色")
    private List<Role> roles;
}
