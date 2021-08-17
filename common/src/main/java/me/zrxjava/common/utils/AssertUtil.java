package me.zrxjava.common.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import me.zrxjava.common.exception.BusinessException;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 * @author void
 * @create 2020-12-29
 */
@UtilityClass
public class AssertUtil {


    /**
     *  为空则抛出自定义异常
     * @param t 验证对象
     * @param x 异常类
     */
    @SneakyThrows
    public <T,X extends Throwable> void isNull(T t, X x){
        if (null == t){
            throw x;
        }
    }

    /**
     * 默认为空则抛出业务异常
     * @param t 验证对象
     * @param errorMsg 错误信息
     */
    public <T> void isNull(T t, String errorMsg){
        if (null == t){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * 默认为空则抛出业务异常
     * @param t 验证对象
     */
    public <T> void isNull(T t){
        isNull(t,"[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     *  不为空为空则抛出自定义异常
     * @param t 验证对象
     * @param x 异常类
     */
    @SneakyThrows
    public <T,X extends Throwable> void nonNull(T t, X x){
        if (null != t){
            throw x;
        }
    }

    /**
     * 默认不为空则抛出业务异常
     * @param t 验证对象
     * @param errorMsg 错误信息
     */
    public <T> void nonNull(T t, String errorMsg){
        if (null != t){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * 默认不为空则抛出业务异常
     * @param t 验证对象
     */
    public <T> void nonNull(T t){
         isNull(t,"[Assertion failed] - this argument is required; it must be null");
    }

    /**
     *  集合为空为空则抛出自定义异常
     * @param collection 验证集合
     * @param x 异常类
     */
    @SneakyThrows
    public <E,T extends Collection<E> , X extends Throwable> void isEmpty(T collection, X x){
        if (collection == null || collection.isEmpty()){
            throw x;
        }
    }

    /**
     * 集合为空为空则抛出业务异常
     * @param collection 验证对象
     * @param errorMsg 错误信息
     */
    public <E,T extends Collection<E>> void isEmpty(T collection, String errorMsg){
        if (collection == null || collection.isEmpty()){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * 集合为空为空则抛出业务异常
     * @param collection 验证集合
     */
    public <E,T extends Collection<E>> void isEmpty(T collection){
        isEmpty(collection,"[Assertion failed] - this collection is required; it must not be empty");
    }

    /**
     *  集合不为空为空则抛出自定义异常
     * @param collection 验证集合
     * @param x 异常类
     */
    @SneakyThrows
    public <E,T extends Collection<E> , X extends Throwable> void isNotEmpty(T collection, X x){
        if (collection != null && !collection.isEmpty()){
            throw x;
        }
    }

    /**
     * 集合不为空为空则抛出业务异常
     * @param collection 验证集合
     * @param errorMsg 错误信息
     */
    public <E,T extends Collection<E>> void isNotEmpty(T collection, String errorMsg){
        if (collection != null && !collection.isEmpty()){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * 集合不为空为空则抛出业务异常
     * @param collection 验证集合
     */
    public <E,T extends Collection<E>> void isNotEmpty(T collection){
        isNotEmpty(collection,"[Assertion failed] - this collection is required; it must be empty");
    }

    /**
     *  map集合为空为空则抛出自定义异常
     * @param map 验证集合
     * @param x 异常类
     */
    @SneakyThrows
    public <K, V, T extends Map<K, V>, X extends Throwable> void isEmpty(T map, X x){
        if (null == map || map.isEmpty()){
            throw x;
        }
    }

    /**
     * map集合为空为空则抛出业务异常
     * @param map 验证对象
     * @param errorMsg 错误信息
     */
    public <K, V, T extends Map<K, V>>void isEmpty(T map, String errorMsg){
        if (null == map || map.isEmpty()){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * map集合为空为空则抛出业务异常
     * @param map 验证对象
     */
    public <K, V, T extends Map<K, V>> void isEmpty(T map){
        isEmpty(map,"[Assertion failed] - this map is required; it must not be empty");
    }

    /**
     *  map集合不为空为空则抛出自定义异常
     * @param map 验证集合
     * @param x 异常类
     */
    @SneakyThrows
    public <K, V, T extends Map<K, V> , X extends Throwable> void isNotEmpty(T map, X x){
        if (null != map && !map.isEmpty()){
            throw x;
        }
    }

    /**
     * map集合不为空为空则抛出业务异常
     * @param map 验证对象
     * @param errorMsg 错误信息
     */
    public <K, V, T extends Map<K, V>> void isNotEmpty(T map, String errorMsg){
        if (null != map && !map.isEmpty()){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * map集合不为空为空则抛出业务异常
     * @param map 验证对象
     */
    public <K, V, T extends Map<K, V>> void isNotEmpty(T map){
        isNotEmpty(map,"[Assertion failed] - this map is required; it must be empty");
    }

    /**
     * 表达式为false时抛出自定义异常
     * @param expression 表达式
     * @param x 自定义异常
     */
    @SneakyThrows
    public <X extends Throwable> void state(boolean expression, X x){
        if (!expression){
            throw x;
        }
    }

    /**
     * 表达式为false时抛出业务异常
     * @param expression 表达式
     * @param errorMsg 错误信息
     */
    public void state(boolean expression, String errorMsg){
        if (!expression){
            throw new BusinessException(errorMsg);
        }
    }

    /**
     * 表达式为false时抛出业务异常
     * @param expression 表达式
     */
    public void state(boolean expression){
        state(expression,"[Assertion failed] - this expression must be true");
    }

}
