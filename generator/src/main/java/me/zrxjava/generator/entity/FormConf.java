package me.zrxjava.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import me.zrxjava.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 表单配置
 * </p>
 *
 * @author void
 * @since 2021-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_form_conf")
@ApiModel(value="FormConf对象", description="表单配置")
public class FormConf extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long tableId;

    @ApiModelProperty(value = "表单信息")
    private String formInfo;

    private String delFlag;

    @ApiModelProperty(value = "所属租户")
    private Integer tenantId;


}
