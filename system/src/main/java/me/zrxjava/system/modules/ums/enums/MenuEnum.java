package me.zrxjava.system.modules.ums.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 * @author void
 * @create 2020-12-04
 */
@Getter
@AllArgsConstructor
public enum MenuEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU(0, "left"),

    /**
     * 顶部菜单
     */
    TOP_MENU(2, "top"),

    /**
     * 按钮
     */
    BUTTON(1, "button");

    /**
     * 类型
     */
    private Integer type;

    /**
     * 描述
     */
    private String description;
}
