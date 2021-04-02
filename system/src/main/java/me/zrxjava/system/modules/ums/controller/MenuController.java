package me.zrxjava.system.modules.ums.controller;

import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import me.zrxjava.system.modules.ums.criteria.MenuCriteria;
import me.zrxjava.system.modules.ums.dto.MenuDto;
import me.zrxjava.system.modules.ums.service.IMenuService;
import me.zrxjava.system.modules.ums.vo.MenuTree;
import me.zrxjava.system.modules.ums.vo.MenuVo;
import me.zrxjava.system.support.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;




/**
 * 系统菜单控制器
 * 
 * @author zrxjava
 * @date 2021-02-24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ums/menu")
@Api(value = "菜单管理",tags = "菜单服务")
public class MenuController
{

    private final IMenuService menuService;
    
    /**
     * 返回当前用户的树形菜单集合
     * @param position 位置
     * @param parentId 父节点ID
     * @return 当前用户的树形菜单
     */
    @GetMapping("/tree")
    @ApiOperation("查询用户菜单")
    @PreAuthorize("@ps.check('ums:menu:tree')")
    public ResponseResult<List<MenuTree>> tree(String position, Integer parentId){
        Set<MenuVo> menus = Sets.newHashSet();
        SecurityUtil.getCurrentUserRoleIds().forEach(roleId -> menus.addAll(menuService.getByRoleId(roleId)));
        return ResponseResult.success(menuService.buildMenuTree(menus, position, parentId));
    }

    /**
     * 查询系统菜单列表
     */
    @PreAuthorize("@ps.check('system:menu:list')")
    @GetMapping("/list")
    @ApiOperation("系统菜单列表")
    public ResponseResult<List<MenuVo>> list(MenuCriteria criteria){
        return ResponseResult.success(menuService.selectList(criteria));
    }


    /**
     * 获取系统菜单详细信息
     */
    @ApiOperation("系统菜单详情")
    @PreAuthorize("@ps.check('system:menu:detail')")
    @GetMapping(value = "/{menuId}")
    public ResponseResult<MenuVo> detail(@PathVariable("menuId") @NotNull Long menuId){
        return ResponseResult.success(menuService.detail(menuId));
    }

    /**
     * 新增系统菜单
     */
    @ApiOperation("系统菜单新增")
    @PreAuthorize("@ps.check('system:menu:add')")
    @Log(title = "系统菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Validated(Insert.class) MenuDto dto){
        return ResponseResult.setBody(menuService.add(dto));
    }

    /**
     * 修改系统菜单
     */
    @ApiOperation("系统菜单修改")
    @PreAuthorize("@ps.check('system:menu:edit')")
    @Log(title = "系统菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseResult<Boolean> edit(@RequestBody @Validated(Update.class) MenuDto dto){
        return ResponseResult.setBody(menuService.edit(dto));
    }

    /**
     * 删除系统菜单
     */
    @ApiOperation("系统菜单删除")
    @PreAuthorize("@ps.check('system:menu:delete')")
    @Log(title = "系统菜单", businessType = BusinessType.DELETE)
	@DeleteMapping
    public ResponseResult<Boolean> delete(@RequestBody @NotEmpty(message = "缺少参数") Set<Long> ids){
        return ResponseResult.setBody(menuService.delete(ids));
    }
}
