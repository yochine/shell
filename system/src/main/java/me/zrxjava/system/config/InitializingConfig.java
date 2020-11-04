package me.zrxjava.system.config;

import me.zrxjava.common.handler.ResponseExcelReturnValueHandler;
import me.zrxjava.common.handler.ResponseSheetWriteHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化配置
 * @author void
 * @create 2020-11-03
 */
@Configuration
public class InitializingConfig implements InitializingBean
{

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Autowired
    private ResponseSheetWriteHandler responseSheetWriteHandler;



    @Override
    public void afterPropertiesSet() {
        // 在spring的setxx属性后加入responseexcel处理器，放在RequestResponseBodyMethodProcessor之前（即@ResponseBody之前）
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        assert returnValueHandlers != null;
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
        this.decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                ResponseExcelReturnValueHandler decorator = new ResponseExcelReturnValueHandler(responseSheetWriteHandler);
                int index = handlers.indexOf(handler);
                handlers.add(index, decorator);
                break;
            }
        }
    }
}
