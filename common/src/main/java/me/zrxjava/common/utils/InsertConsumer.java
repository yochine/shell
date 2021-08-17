package me.zrxjava.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 利用并行流快速插入数据
 * @author void
 * @create 2021-06-03
 */
public class InsertConsumer {

    /**
     * 每个长 SQL 插入的行数，可以根据数据库性能调整
     */
    private final static int SIZE = 1000;

    /**
     * 它默认的线程数量就是你的处理器数量， 这个值是由Runtime.getRuntime().available-
     * Processors()得到的。
     * 这是一个全局设置，因此它将影响代码中所有的并行流。反过来说，目前还无法专为某个
     * 并行流指定这个值。一般而言，让ForkJoinPool的大小等于处理器数量是个不错的默认值，
     * 除非你有很好的理由，否则我们强烈建议你不要修改它。
     */
    static {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
    }

    /**
     * 插入方法
     *  InsertConsumer.insertData(list, mapper::insertList);
     * @param list     插入数据集合
     * @param consumer 消费型方法，直接使用 mapper::method 方法引用的方式
     * @param <T>      插入的数据类型
     */
    public static <T> void insertData(List<T> list, Consumer<List<T>> consumer) {
        if (list == null || list.size() < 1) {
            return;
        }

        List<List<T>> streamList = new ArrayList<>();

        for (int i = 0; i < list.size(); i += SIZE) {
            int j = Math.min((i + SIZE), list.size());
            List<T> subList = list.subList(i, j);
            streamList.add(subList);
        }
        // 并行流使用的并发数是 CPU 核心数，不能局部更改。全局更改影响较大，斟酌
        streamList.parallelStream().forEach(consumer);
    }
}
