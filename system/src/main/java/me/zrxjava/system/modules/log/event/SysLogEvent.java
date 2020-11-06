package me.zrxjava.system.modules.log.event;

import me.zrxjava.system.modules.log.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author lengleng 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog source) {
        super(source);
    }

}
