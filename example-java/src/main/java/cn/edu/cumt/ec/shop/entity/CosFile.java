package cn.edu.cumt.ec.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 云存储文件表
 * </p>
 *
 * @author 学习
 * @since 2020-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CosFile对象", description="云存储文件表")
public class CosFile implements Serializable {

    @TableId(value = "cos_file_id")
    @ApiModelProperty(value = "雪花id")
    private Long cosFileId;

    @ApiModelProperty(value = "图片，视频，音频，文档，压缩包，其它，数据字典")
    private String fileType;

    @ApiModelProperty(value = "文件扩展名")
    private String fileExtName;

    @ApiModelProperty(value = "原文件名")
    private String originFileName;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "cos文件全路径")
    private String cosUrl;

    @ApiModelProperty(value = "cos key")
    private String cosKey;

    @ApiModelProperty(value = "相关表名")
    private String relatedTableName;

    @ApiModelProperty(value = "相关id")
    private Long relatedId;

    @ApiModelProperty(value = "区分标记")
    private String tag;

    @ApiModelProperty(value = "文件大小【位】")
    private Integer fileSize;

    @ApiModelProperty(value = "上传人id")
    private Long uploaderId;

    @ApiModelProperty(value = "上传人姓名")
    private String uploaderName;

    @ApiModelProperty(value = "录入时间")
    private LocalDateTime uploadDatetime;

    @ApiModelProperty(value = "删除状态，0：未删除，1：已删除")
    private String deleted;


}
