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
import me.zrxjava.system.modules.criteria.DictCriteria;
import me.zrxjava.system.modules.dto.DictDto;
import me.zrxjava.system.modules.service.IDictService;
import me.zrxjava.system.modules.vo.DictVo;
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
 * 数据字典控制器
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Api("数据字典")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict")
public class DictController
{

    private final IDictService dictService;

    /**
     * 查询数据字典列表
     */
    @PreAuthorize("@ps.check('system:dict:list')")
    @GetMapping("/list")
    @ApiOperation("数据字典列表")
    public ResponseResult<Page<DictVo>> list(DictCriteria criteria){
        return ResponseResult.success(dictService.selectPage(criteria));
    }

    /**
     * 获取数据字典详细信息
     */
    @ApiOperation("数据字典详情")
    @PreAuthorize("@ps.check('system:dict:detail')")
    @GetMapping(value = "/{dictId}")
    public ResponseResult<DictVo> detail(@PathVariable("dictId") @NotNull Long dictId){
        return ResponseResult.success(dictService.detail(dictId));
    }

    /**
     * 新增数据字典
     */
    @ApiOperation("数据字典新增")
    @PreAuthorize("@ps.check('system:dict:add')")
    @Log(title = "数据字典", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Validated(Insert.class) DictDto dto){
        return ResponseResult.setBody(dictService.add(dto));
    }

    /**
     * 修改数据字典
     */
    @ApiOperation("数据字典修改")
    @PreAuthorize("@ps.check('system:dict:edit')")
    @Log(title = "数据字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseResult<Boolean> edit(@RequestBody @Validated(Update.class) DictDto dto){
        return ResponseResult.setBody(dictService.edit(dto));
    }

    /**
     * 删除数据字典
     */
    @ApiOperation("数据字典删除")
    @PreAuthorize("@ps.check('system:dict:delete')")
    @Log(title = "数据字典", businessType = BusinessType.DELETE)
	@DeleteMapping
    public ResponseResult<Boolean> delete(@RequestBody @NotEmpty(message = "缺少参数") Set<Long> ids){
        return ResponseResult.setBody(dictService.delete(ids));
    }
}
