package me.zrxjava.generator.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import me.zrxjava.generator.config.GenConfig;
import me.zrxjava.generator.constants.GenConstants;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.entity.TableColumn;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 代码生成器 工具类
 *
 * @author void
 */
public class GenUtils
{

    public static final String isPk = "1";

    /**
     * 初始化表信息
     */
    public static void initTable(Table table, String operName, GenConfig genConfig)
    {
        table.setClassName(convertClassName(table.getTableName(),genConfig));
        table.setPackageName(genConfig.getPackageName());
        table.setModuleName(StrUtil.subAfter(genConfig.getPackageName(),'.',true));
        table.setBusinessName(StrUtil.subAfter(table.getTableName(),'_',true));
        table.setFunctionName(RegExUtils.replaceAll(table.getTableComment(), "(?:表)", ""));
        table.setFunctionAuthor(genConfig.getAuthor());
        table.setCreateBy(operName);
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(TableColumn column, Table table)
    {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getTableId());
        column.setCreateBy(table.getCreateBy());
        // 设置java字段名
        column.setJavaField(StrUtil.upperFirst(StrUtil.toCamelCase(columnName)));

        if (ArrayUtil.containsAny(GenConstants.COLUMNTYPE_STR, dataType))
        {
            column.setJavaType(GenConstants.TYPE_STRING);
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        }
        if (ArrayUtil.containsAny(GenConstants.COLUMNTYPE_TIME, dataType))
        {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        }
        if (ArrayUtil.containsAny(GenConstants.COLUMNTYPE_NUMBER, dataType))
        {
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点型 统一用BigDecimal
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0)
            {
                column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10)
            {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else
            {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        // 插入字段（默认所有字段都需要插入）
        column.setIsInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!ArrayUtil.containsAny(GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !isPk.equals(column.getIsPk()))
        {
            column.setIsEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!ArrayUtil.containsAny(GenConstants.COLUMNNAME_NOT_LIST, columnName) && !isPk.equals(column.getIsPk()))
        {
            column.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!ArrayUtil.containsAny(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !isPk.equals(column.getIsPk()))
        {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name"))
        {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status"))
        {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        if (StringUtils.endsWithIgnoreCase(columnName, "type")
                || StringUtils.endsWithIgnoreCase(columnName, "sex"))
        {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 文件字段设置上传控件
        if (StringUtils.endsWithIgnoreCase(columnName, "image"))
        {
            column.setHtmlType(GenConstants.HTML_UPLOAD_IMAGE);
        }
        // 内容字段设置富文本控件
        if (StringUtils.endsWithIgnoreCase(columnName, "content"))
        {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    /**
     * 表名转换成Java类名
     * 
     * @param tableName 表名称
     * @param genConfig
     * @return 类名
     */
    public static String convertClassName(String tableName, GenConfig genConfig)
    {
        Boolean autoRemovePre = genConfig.getAutoRemovePre();
        String tablePrefix = genConfig.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix))
        {
            //去除前缀
            tableName = tableName.substring(tablePrefix.length());
        }
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    /**
     * 获取数据库类型字段
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType)
    {
        if (StringUtils.indexOf(columnType, "(") > 0)
        {
            return StringUtils.substringBefore(columnType, "(");
        }
        return columnType;
    }

    /**
     * 获取字段长度
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType)
    {
        if (StringUtils.indexOf(columnType, "(") > 0)
        {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        }
        else
        {
            return 0;
        }
    }
}
