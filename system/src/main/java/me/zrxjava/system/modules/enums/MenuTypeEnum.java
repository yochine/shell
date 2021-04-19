package me.zrxjava.system.modules.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 * @author void
 * @create 2020-12-04
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 目录
     */
    CATALOG(0, "left"),

    /**
     * 菜单
     */
    MENU(1, "top"),

    /**
     * 按钮
     */
    BUTTON(2, "button");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String description;
}
