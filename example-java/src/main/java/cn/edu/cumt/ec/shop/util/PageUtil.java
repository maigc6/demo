package cn.edu.cumt.ec.shop.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author edward
 */
@Data
public class PageUtil {

    @TableField(exist = false)
    long current = 1L;

    @TableField(exist = false)
    long size = 10L;

    @ApiModelProperty(value = "ζεΊθ§ε")
    private List<OrderItem> orders;

}
