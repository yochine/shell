package me.zrxjava.system.modules.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import me.zrxjava.system.modules.criteria.DictDetailCriteria;
import me.zrxjava.system.modules.dto.DictDetailDto;
import me.zrxjava.system.modules.service.IDictDetailService;
import me.zrxjava.system.modules.vo.DictDetailVo;
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
import java.util.Set;


/**
 * 数据字典详情控制器
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Api("数据字典详情")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict/detail")
public class DictDetailController
{

    private final IDictDetailService dictDetailService;

    /**
     * 查询数据字典详情列表
     */
    @PreAuthorize("@ps.check('system:dictDetail:list')")
    @GetMapping("/list")
    @ApiOperation("数据字典详情列表")
    public ResponseResult<Page<DictDetailVo>> list(DictDetailCriteria criteria){
        return ResponseResult.success(dictDetailService.selectPage(criteria));
    }

    /**
     * 获取数据字典详情详细信息
     */
    @ApiOperation("数据字典详情详情")
    @PreAuthorize("@ps.check('system:dictDetail:detail')")
    @GetMapping(value = "/{dictDetailId}")
    public ResponseResult<DictDetailVo> detail(@PathVariable("dictDetailId") @NotNull Long dictDetailId){
        return ResponseResult.success(dictDetailService.detail(dictDetailId));
    }

    /**
     * 新增数据字典详情
     */
    @ApiOperation("数据字典详情新增")
    @PreAuthorize("@ps.check('system:dictDetail:add')")
    @Log(title = "数据字典详情", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Validated(Insert.class) DictDetailDto dto){
        return ResponseResult.setBody(dictDetailService.add(dto));
    }

    /**
     * 修改数据字典详情
     */
    @ApiOperation("数据字典详情修改")
    @PreAuthorize("@ps.check('system:dictDetail:edit')")
    @Log(title = "数据字典详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseResult<Boolean> edit(@RequestBody @Validated(Update.class) DictDetailDto dto){
        return ResponseResult.setBody(dictDetailService.edit(dto));
    }

    /**
     * 删除数据字典详情
     */
    @ApiOperation("数据字典详情删除")
    @PreAuthorize("@ps.check('system:dictDetail:delete')")
    @Log(title = "数据字典详情", businessType = BusinessType.DELETE)
	@DeleteMapping
    public ResponseResult<Boolean> delete(@RequestBody @NotEmpty(message = "缺少参数")  Set<Long> ids){
        return ResponseResult.setBody(dictDetailService.delete(ids));
    }
}
