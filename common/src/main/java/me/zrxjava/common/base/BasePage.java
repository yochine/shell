package me.zrxjava.common.base;

import lombok.Data;

/**
 * @author void
 * @create 2020-12-24
 */
@Data
public class BasePage {

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * 排序字段
     */
    private String sort;

    /** 通用查询字段 */
    private String searchName;

    /** 排序的方向desc或者asc */
    private String isAsc = "asc";
}
