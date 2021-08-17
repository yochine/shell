package me.zrxjava.common.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import me.zrxjava.common.injector.methods.SelectCountByScope;
import me.zrxjava.common.injector.methods.SelectListByScope;
import me.zrxjava.common.injector.methods.SelectPageByScope;

import java.util.List;

/**
 * 通用sql注入器
 * @author void
 * @create 2020-12-16
 */
public class CommonInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectListByScope());
        methodList.add(new SelectPageByScope());
        methodList.add(new SelectCountByScope());
        /*不需要的字段使用predicate 构造器注入*/
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
