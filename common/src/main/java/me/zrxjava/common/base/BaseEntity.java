package me.zrxjava.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用字段
 * @author void
 * @create 2020-09-16
 */
@Setter
@Getter
public class BaseEntity implements Serializable {

    @TableField(value = "create_by",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updatedBy;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updateTime;

//    @TableField(value = "delete_flag")
//    private Integer deleteFlag;

}
