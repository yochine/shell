package me.zrxjava.system.modules.log.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.system.modules.log.entity.SysLog;
import me.zrxjava.system.modules.log.event.SysLogEvent;
import me.zrxjava.system.modules.log.service.ISysLogService;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 日志监听器
 * @author void
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysLogListener {

	private final ISysLogService sysLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = (SysLog) event.getSource();
		sysLogService.save(sysLog);
	}

}
