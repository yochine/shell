package me.zrxjava.generator.constants;

/**
 * 代码生成通用常量
 *
 * @author void
 */
public class GenConstants
{
    /** 单表（增删改查） */
    public static final String TPL_CRUD = "crud";

    /** 树表（增删改查） */
    public static final String TPL_TREE = "tree";
    /** 关系表 */
    public static final String TPL_RELATION = "relation";

    /** 树编码字段 */
    public static final String TREE_CODE = "treeCode";

    /** 树父编码字段 */
    public static final String TREE_PARENT_CODE = "treeParentCode";

    /** 树名称字段 */
    public static final String TREE_NAME = "treeName";

    /** 上级菜单ID字段 */
    public static final String PARENT_MENU_ID = "parentMenuId";

    /** 上级菜单名称字段 */
    public static final String PARENT_MENU_NAME = "parentMenuName";

    /** 数据库字符串类型 */
    public static final String[] COLUMNTYPE_STR = { "char", "varchar", "nvarchar", "varchar2", "tinytext", "text",
            "mediumtext", "longtext" };

    /** 数据库时间类型 */
    public static final String[] COLUMNTYPE_TIME = { "datetime", "time", "date", "timestamp" };

    /** 数据库数字类型 */
    public static final String[] COLUMNTYPE_NUMBER = { "tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bit", "bigint", "float", "double", "decimal" };

    /** 页面不需要编辑字段 */
    public static final String[] COLUMNNAME_NOT_EDIT = { "id", "create_by", "create_time", "del_flag", "update_by",
            "update_time" };

    /** 页面不需要显示的列表字段 */
    public static final String[] COLUMNNAME_NOT_LIST = { "id", "create_by", "create_time", "del_flag", "update_by",
            "update_time" };

    /** 页面不需要查询字段 */
    public static final String[] COLUMNNAME_NOT_QUERY = { "id", "create_by", "create_time", "del_flag", "update_by",
            "update_time", "remark" };

    /** Entity基类字段 */
    public static final String[] BASE_ENTITY = { "createBy", "createTime", "updateBy", "updateTime", "remark" };

    /** Tree基类字段 */
    public static final String[] TREE_ENTITY = { "parentName", "parentId", "orderNum", "ancestors", "children" };

    /** 文本框 */
    public static final String HTML_INPUT = "input";

    /** 文本域 */
    public static final String HTML_TEXTAREA = "textarea";

    /** 下拉框 */
    public static final String HTML_SELECT = "select";

    /** 单选框 */
    public static final String HTML_RADIO = "radio";

    /** 复选框 */
    public static final String HTML_CHECKBOX = "checkbox";

    /** 日期控件 */
    public static final String HTML_DATETIME = "datetime";

    /** 上传控件 */
    public static final String HTML_UPLOAD_IMAGE = "uploadImage";

    /** 富文本控件 */
    public static final String HTML_EDITOR = "editor";

    /** 字符串类型 */
    public static final String TYPE_STRING = "String";

    /** 整型 */
    public static final String TYPE_INTEGER = "Integer";

    /** 长整型 */
    public static final String TYPE_LONG = "Long";

    /** 浮点型 */
    public static final String TYPE_DOUBLE = "Double";

    /** 高精度计算类型 */
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    /** 时间类型 */
    public static final String TYPE_DATE = "LocalDateTime";

    /** 模糊查询 */
    public static final String QUERY_LIKE = "LIKE";

    /** 需要 */
    public static final String REQUIRE = "1";

    /** crud前缀 */
    public static final String CRUD_PREFIX = "export const tableOption =";

    public static final String ENTITY_JAVA_VM = "Entity.java.vm";

    public static final String MAPPER_JAVA_VM = "Mapper.java.vm";

    public static final String SERVICE_JAVA_VM = "Service.java.vm";

    public static final String SERVICE_IMPL_JAVA_VM = "ServiceImpl.java.vm";

    public static final String CONTROLLER_JAVA_VM = "Controller.java.vm";

    public static final String MAPPER_XML_VM = "Mapper.xml.vm";

    public static final String MENU_SQL_VM = "menu.sql.vm";

    public static final String AVUE_INDEX_VUE_VM = "avue/index.vue.vm";

    public static final String ELE_INDEX_VUE_VM = "element/index.vue.vm";

    public static final String ELE_ADD_UPDATE_VUE_VM = "element/form.vue.vm";

    public static final String AVUE_API_JS_VM = "avue/api.js.vm";

    public static final String AVUE_CRUD_JS_VM = "avue/crud.js.vm";
}
