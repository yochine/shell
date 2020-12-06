package me.zrxjava.system.modules.ums.controller;


import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.system.modules.ums.service.IMenuService;
import me.zrxjava.system.modules.ums.vo.MenuTree;
import me.zrxjava.system.modules.ums.vo.MenuVo;
import me.zrxjava.system.support.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ums/menu")
@Api(value = "菜单管理",tags = "菜单服务")
public class MenuController {


    private final IMenuService menuService;


    /**
     * 返回当前用户的树形菜单集合
     * @param type 类型
     * @param parentId 父节点ID
     * @return 当前用户的树形菜单
     */
    @GetMapping("/list")
    @ApiOperation("查询用户菜单")
    @PreAuthorize("@ps.check('ums:menu:list')")
    public ResponseResult<List<MenuTree>> list(String type, Integer parentId){
        Set<MenuVo> menus = Sets.newHashSet();
        SecurityUtil.getCurrentUserRoleIds().forEach(roleId -> menus.addAll(menuService.getByRoleId(roleId)));
        return ResponseResult.success(menuService.buildMenuTree(menus, type, parentId));
    }
}
