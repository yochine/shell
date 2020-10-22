package me.zrxjava.system.config.thread;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * 异步线程加入主线程的traceId
 * @author void
 * @create 2020-10-22
 */
public class MdcTaskDecorator implements TaskDecorator {

    /**
     * 使异步线程池获得主线程的上下文
     * @param runnable
     * @return
     */

    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String,String> map = MDC.getCopyOfContextMap();
        return () -> {
            try{
                MDC.setContextMap(map);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
