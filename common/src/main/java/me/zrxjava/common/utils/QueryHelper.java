package me.zrxjava.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.annotation.Query;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mp 查询构造器组件
 * @author void
 * @create 2020-11-09
 */
@Slf4j
@SuppressWarnings({"unchecked", "all"})
public class QueryHelper {
    public static ThreadLocal<QueryWrapper> queryWrapperThreadLocal = new ThreadLocal<>();
    protected static Map<String, List<Field>> FIELD_CACHE = new ConcurrentHashMap<>(16);
    protected static Map<String, String> COLUMN_CACHE = new ConcurrentHashMap<>(16);
    public static final String[] pageParams= {"size","current","isAsc","serialVersionUID"};

    /**
     * Mybatis Plus  查询构建
     *
     * @param criteria
     * @param clazz
     * @param <T>
     * @return /
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Object criteria, Class<T> clazz) {
        QueryWrapper<T> queryWrapper = Wrappers.query();
        final List<Field> allFields = getAllFields(criteria.getClass());
        allFields.forEach(field -> {
            buildQuery(criteria, field, clazz, queryWrapper);
        });
        return queryWrapper;
    }

    /**
     * 构造查询query
     * @param criteria 查询条件
     * @param field 条件字段
     * @param clazz entity
     * @param queryWrapper
     * @param <T>
     */
    private static <T> void buildQuery(Object criteria, Field field, Class<T> clazz, QueryWrapper<T> queryWrapper) {
        final Query query = field.getAnnotation(Query.class);
        Object value = null;
        final TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        boolean accessible = field.isAccessible();
        String attributeName = null;
        try {
            // 设置对象的访问权限，保证对private的属性的访
            field.setAccessible(true);
            value = field.get(criteria);
            // 值为空直接返回
            if (Objects.isNull(value)) {
                return;
            }
            // 分页字段直接返回
            if (StrUtil.containsAny(field.getName(),pageParams)){
                return;
            }
            attributeName = getTableColumnFromField(tableInfo, clazz.getName(), field.getName());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            return;
        } finally {
            field.setAccessible(accessible);
        }
        if (Objects.nonNull(query)) {
            String propName = query.propName();
            String blurry = query.blurry();
            attributeName = StringUtils.isBlank(propName) ? attributeName : propName;
            if (StringUtils.isNotBlank(blurry)) {
                String[] blurrys = blurry.split(",");
                for (String item : blurrys) {
                    queryWrapper.like(item, value);
                }
            }
            List<Object> between = null;
            switch (query.type()) {
                case EQ:
                    queryWrapper.eq(attributeName, value);
                    break;
                case GE:
                    queryWrapper.ge(attributeName, value);
                    break;
                case LE:
                    queryWrapper.le(attributeName, value);
                    break;
                case LT:
                    queryWrapper.lt(attributeName, value);
                    break;
                case LIKE:
                    queryWrapper.like(attributeName, value);
                    break;
                case IN:
                    between = new ArrayList<>((List<Object>) value);
                    queryWrapper.in(attributeName, between.toArray());
                    break;
                case NE:
                    queryWrapper.ne(attributeName, value);
                    break;
                case IS_NOT_NULL:
                    queryWrapper.isNotNull(attributeName);
                    break;
                case IS_NULL:
                    queryWrapper.isNull(attributeName);
                    break;
                case BETWEEN:
                    between = new ArrayList<>((List<Object>) value);
                    queryWrapper.in(attributeName, between.toArray());
                    break;
                case ORDER_BY:
                    queryWrapper.orderByAsc(attributeName);
                    break;
                    // todo 扩展 groupby  or having leftlike rightlike
                default:
                    break;
            }
        } else {
            queryWrapper.eq(attributeName, value);
        }
    }

    /**
     * 依据Mybatis Plus 获取 Database 真实Column 字段
     *
     * @param tableInfo
     * @param field
     * @return /
     */
    public static String getTableColumnFromField(TableInfo tableInfo, String className, String field) {
        String columnName = null;
        final String key = className + "_" + field;
        if (FIELD_CACHE.containsKey(key)) {
            columnName = COLUMN_CACHE.get(field);
        } else {
            final String name = field;
            for (TableFieldInfo item : tableInfo.getFieldList()) {
                if (item.getField().getName().equals(name)) {
                    columnName = item.getColumn();
                    COLUMN_CACHE.put(key, columnName);
                    break;
                }
            }
        }
        return columnName;
    }

    /**
     * 获取查询实体字段集合
     * @param criteria
     * @return
     */
    public static List<Field> getAllFields(Class criteria) {
        List<Field> fields = null;
        if (criteria != null) {
            if (FIELD_CACHE.containsKey(criteria.getName())) {
                fields = FIELD_CACHE.get(criteria.getName());
            } else {
                fields = new ArrayList<>(Arrays.asList(criteria.getDeclaredFields()));
                final List<Field> allFields = getAllFields(criteria.getSuperclass());
                if (CollUtil.isNotEmpty(allFields)) {
                    fields.addAll(allFields);
                }
                FIELD_CACHE.put(criteria.getName(), fields);
            }
        }
        return fields;
    }
}

/**
 * @author liaojinlong
 * @since 2020/7/6 21:04
 */
class ElField {
    private String column;
    private boolean status;

    public ElField(String column, boolean status) {
        this.column = column;
        this.status = status;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
