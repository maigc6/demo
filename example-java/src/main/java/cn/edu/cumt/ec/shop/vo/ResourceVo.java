package cn.edu.cumt.ec.shop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author baomidou
 * @since 2022-05-06
 */
@Data
@ApiModel(value = "ResourceVo对象", description = "ResourceVo")
public class ResourceVo implements Serializable {

    @ApiModelProperty("资源id")
    private Long resourceId;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("资源类型，1：菜单，2：按钮")
    private String resourceType;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("显示顺序")
    private Integer showSequence;

    @ApiModelProperty("子项")
    private List<ResourceVo> children;

}
