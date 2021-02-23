package me.zrxjava.generator.service;

import me.zrxjava.generator.entity.FormConf;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 表单配置 服务类
 * </p>
 *
 * @author void
 * @since 2021-02-05
 */
public interface IFormConfService extends IService<FormConf> {

    /**
     * 获取表单配置信息
     * 不存在则根据模板自动生成
     * @param tableId 表名id
     * @param dsName 数据源
     * @return 表单配置
     */
    String detail(Long tableId, String dsName);
}
