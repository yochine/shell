package me.zrxjava.system.support.intercept;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.base.DataScope;
import me.zrxjava.system.support.util.SecurityUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 数据权限拦截器
 * @author void
 * @create 2020-12-08
 */
@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 先判断是不是SELECT操作 不是直接过滤
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        // 执行的SQL语句
        String originalSql = boundSql.getSql();
        // SQL语句的参数
        Object parameterObject = boundSql.getParameterObject();
        //查找参数中包含DataScope类型的参数
        DataScope dataScope = findDataScopeObject(parameterObject);
        if (dataScope == null) {
            return invocation.proceed();
        }
        String scopeName = dataScope.getScopeName();
        Set<Long> deptIds = SecurityUtil.getCurrentUserDataScopes();
        // 优先获取赋值数据 空则为个人权限
        if (CollUtil.isEmpty(deptIds)) {
            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope."
                    + scopeName + " = " + SecurityUtil.getCurrentUserId() + "";
            metaObject.setValue("delegate.boundSql.sql", originalSql);
            return invocation.proceed();
        }
        // 超级管理员权限
        if (deptIds.contains(0L)){
            return invocation.proceed();
        }
        // 自定义或者下级或本级权限
        String join = CollectionUtil.join(deptIds, ",");
        String dataScopeSql = "select user_id from sys_user where deptId in  (" + join + ")";
        originalSql = String.format("SELECT * FROM (%s) temp_data_scope WHERE temp_data_scope.%s IN (%s)",
                 originalSql, scopeName, dataScopeSql);
        metaObject.setValue("delegate.boundSql.sql", originalSql);
        return invocation.proceed();
    }

    /**
     * 查找参数是否包括DataScope对象
     * @param parameterObj 参数列表
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObj) {
        if (parameterObj instanceof DataScope) {
            return (DataScope) parameterObj;
        }
        else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return (DataScope) val;
                }
            }
        }
        return null;
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
