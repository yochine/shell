package me.zrxjava.system.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zrxjava.system.modules.log.entity.SysLog;
import me.zrxjava.system.modules.log.mapper.SysLogMapper;
import me.zrxjava.system.modules.log.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 * @author void
 * @create 2020-11-06
 */

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
}
