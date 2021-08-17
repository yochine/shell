package me.zrxjava.system.modules.event;

import me.zrxjava.system.modules.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author lengleng 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog source) {
        super(source);
    }

}
