package me.zrxjava.generator.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import me.zrxjava.common.exception.BusinessException;
import me.zrxjava.generator.constants.GenConstants;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.entity.TableColumn;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 模板处理工具类
 * 
 * @author void
 */
public class VelocityUtils
{
    /** 项目空间路径 */
    private static final String PROJECT_PATH = "main/java";

    /** mybatis空间路径 */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /** 默认上级菜单，系统工具 */
    private static final String DEFAULT_PARENT_MENU_ID = "3";

    private static final String DEFAULT_TRUE = "1";


    /**
     * 开始渲染
     * @return
     */
    public static Map<String, String> startRendering(Table table, List<TableColumn> columns){
        Map<String, String> dataMap = new LinkedHashMap<>();
        VelocityInitializer.initVelocity();
        VelocityContext context = prepareContext(table,columns);
        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "utf-8");
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;

    }

    /**
     * 生成代码到指定路径
     * @param table
     * @param tableColumns
     */
    public static void generate(Table table, List<TableColumn> tableColumns) {
        VelocityInitializer.initVelocity();
        VelocityContext context = prepareContext(table,tableColumns);
        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            try (StringWriter sw = new StringWriter()){

                Template tpl = Velocity.getTemplate(template, "utf-8");
                tpl.merge(context, sw);
                String path = getGenPath(table, template);
                FileUtils.writeStringToFile(new File(path), sw.toString(), "utf-8");
            } catch (IOException e) {
                throw new BusinessException("渲染模板失败，表名：" + table.getTableName());
            }
        }
    }

    /**
     * 下载生成代码
     * @param table
     * @param tableColumns
     * @param zip
     */
    public static void download(Table table, List<TableColumn> tableColumns, ZipOutputStream zip) {
        VelocityInitializer.initVelocity();
        VelocityContext context = prepareContext(table,tableColumns);
        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            try(StringWriter sw = new StringWriter()) {
                Template tpl = Velocity.getTemplate(template, "utf-8");
                tpl.merge(context, sw);
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, "utf-8");
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                throw new BusinessException("渲染模板失败，表名：" + table.getTableName());
            }
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table 业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(Table table, String template)
    {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/"))
        {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(Table genTable,List<TableColumn> columns)
    {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("tableComment", genTable.getTableComment());
        velocityContext.put("functionName", StringUtils.isNotBlank(genTable.getFunctionName()) ? genTable.getFunctionName() : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("basePackage", StrUtil.subBefore(genTable.getPackageName(),'.',true));
        velocityContext.put("packageName", genTable.getPackageName());
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", LocalDate.now());
        velocityContext.put("pkColumn", getPkColumn(columns));
        velocityContext.put("importList", getImportList(columns));
        velocityContext.put("permissionPrefix", String.format("%s:%s", genTable.getModuleName(), genTable.getBusinessName()));
        velocityContext.put("columns", columns);
        velocityContext.put("table", genTable);
        setMenuVelocityContext(velocityContext, genTable);
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory()))
        {
            setTreeVelocityContext(velocityContext, genTable,columns);
        }
        return velocityContext;
    }

    /**
     * 获取主键
     * @param columns
     * @return
     */
    private static Optional<TableColumn> getPkColumn(List<TableColumn> columns) {
        Optional<TableColumn> tableColumn = columns.stream().filter(column -> column.getIsPk().equals(DEFAULT_TRUE)).findFirst();
        if (tableColumn.isPresent()){
            return tableColumn;
        }

        return Optional.of(columns.get(0));
    }

    public static void setMenuVelocityContext(VelocityContext context, Table genTable)
    {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String parentMenuId = getParentMenuId(paramsObj);
        context.put("parentMenuId", parentMenuId);
    }

    public static void setTreeVelocityContext(VelocityContext context, Table genTable, List<TableColumn> columns)
    {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeCode = getTreecode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("treeParentCode", treeParentCode);
        context.put("treeName", treeName);
        context.put("expandColumn", getExpandColumn(genTable,columns));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
        {
            context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME))
        {
            context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
        }
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory)
    {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/entity.java.vm");
        templates.add("vm/java/dto.java.vm");
        templates.add("vm/java/vo.java.vm");
        templates.add("vm/java/criteria.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/transfer.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        templates.add("vm/sql/sql.vm");
        templates.add("vm/js/api.js.vm");
        if (GenConstants.TPL_CRUD.equals(tplCategory))
        {
            templates.add("vm/avue/index.vue.vm");
        }
        else if (GenConstants.TPL_TREE.equals(tplCategory))
        {
            templates.add("vm/avue/index-tree.vue.vm");
        }
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, Table genTable)
    {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = genTable.getBusinessName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;
        String vuePath = "vue";

        if (template.contains("entity.java.vm"))
        {
            fileName = String.format("%s/entity/%s.java", javaPath, className);
        }
        else if (template.contains("dto.java.vm"))
        {
            fileName = String.format("%s/dto/%sDto.java", javaPath, className);
        }
        else if (template.contains("vo.java.vm"))
        {
            fileName = String.format("%s/vo/%sVo.java", javaPath, className);
        }
        else if (template.contains("transfer.java.vm"))
        {
            fileName = String.format("%s/transfer/%sTransfer.java", javaPath, className);
        }
        else if (template.contains("criteria.java.vm"))
        {
            fileName = String.format("%s/criteria/%sCriteria.java", javaPath, className);
        }
        else if (template.contains("mapper.java.vm"))
        {
            fileName = String.format("%s/mapper/%sMapper.java", javaPath, className);
        }
        else if (template.contains("service.java.vm"))
        {
            fileName = String.format("%s/service/I%sService.java", javaPath, className);
        }
        else if (template.contains("serviceImpl.java.vm"))
        {
            fileName = String.format("%s/service/impl/%sServiceImpl.java", javaPath, className);
        }
        else if (template.contains("controller.java.vm"))
        {
            fileName = String.format("%s/controller/%sController.java", javaPath, className);
        }
        else if (template.contains("mapper.xml.vm"))
        {
            fileName = String.format("%s/%sMapper.xml", mybatisPath, className);
        }
        else if (template.contains("sql.vm"))
        {
            fileName = businessName + "Menu.sql";
        }
        else if (template.contains("api.js.vm"))
        {
            fileName = String.format("%s/api/%s/%s.js", vuePath, moduleName, businessName);
        }
        else if (template.contains("index.vue.vm"))
        {
            fileName = String.format("%s/views/%s/%s/index.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("index-tree.vue.vm"))
        {
            fileName = String.format("%s/views/%s/%s/index.vue", vuePath, moduleName, businessName);
        }
        return fileName;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param columns 列集合
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<TableColumn> columns)
    {
        HashSet<String> importList = new HashSet<String>();
        for (TableColumn column : columns)
        {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType()))
            {
                importList.add("java.time.LocalDateTime");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            }
            else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType()))
            {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取上级菜单ID字段
     *
     * @param paramsObj 生成其他选项
     * @return 上级菜单ID字段
     */
    public static String getParentMenuId(JSONObject paramsObj)
    {
        if (Objects.nonNull(paramsObj) && paramsObj.containsKey(GenConstants.PARENT_MENU_ID))
        {
            return paramsObj.getString(GenConstants.PARENT_MENU_ID);
        }
        return DEFAULT_PARENT_MENU_ID;
    }

    /**
     * 获取树编码
     *
     * @param paramsObj 生成其他选项
     * @return 树编码
     */
    public static String getTreecode(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_CODE))
        {
            return StrUtil.upperFirst(StrUtil.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE)));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树父编码
     *
     * @param paramsObj 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
        {
            return StrUtil.upperFirst(StrUtil.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE)));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树名称
     *
     * @param paramsObj 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_NAME))
        {
            return StrUtil.upperFirst(StrUtil.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME)));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     *
     * @param genTable 业务表对象
     * @param columns
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(Table genTable, List<TableColumn> columns)
    {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeName = paramsObj.getString(GenConstants.TREE_NAME);
        int num = 0;
        for (TableColumn column : columns)
        {
            if (column.isList())
            {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName))
                {
                    break;
                }
            }
        }
        return num;
    }


}
