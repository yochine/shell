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
 * 代码生成业务表
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table")
@ApiModel(value="Table对象", description="代码生成业务表")
public class Table extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "table_id", type = IdType.AUTO)
    private Long tableId;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "表描述")
    private String tableComment;

    @ApiModelProperty(value = "实体类名称")
    private String className;

    @ApiModelProperty(value = "使用的模板（crud单表操作 tree树表操作）")
    private String tplCategory;

    @ApiModelProperty(value = "生成包路径")
    private String packageName;

    @ApiModelProperty(value = "生成模块名")
    private String moduleName;

    @ApiModelProperty(value = "生成业务名")
    private String businessName;

    @ApiModelProperty(value = "生成功能名")
    private String functionName;

    @ApiModelProperty(value = "生成功能作者")
    private String functionAuthor;

    @ApiModelProperty(value = "生成代码方式（0zip压缩包 1自定义路径）")
    private String genType;

    @ApiModelProperty(value = "生成路径（不填默认项目路径）")
    private String genPath;

    @ApiModelProperty(value = "其它生成选项")
    private String options;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;


}
