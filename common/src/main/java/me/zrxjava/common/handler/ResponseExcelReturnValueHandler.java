package me.zrxjava.common.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.annotation.ResponseExcel;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理@ResponseExcel 返回值
 *
 * @author lengleng
 */
@Slf4j
@RequiredArgsConstructor
public class ResponseExcelReturnValueHandler implements HandlerMethodReturnValueHandler {

	private final ResponseSheetWriteHandler responseSheetWriteHandler;


	/**
	 * 只处理@ResponseExcel 声明的方法
	 *
	 * @param parameter 方法签名
	 * @return 是否处理
	 */
	@Override
	public boolean supportsReturnType(MethodParameter parameter) {
		return parameter.getMethodAnnotation(ResponseExcel.class) != null;
	}

	/**
	 * 处理逻辑
	 *
	 * @param o                返回参数
	 * @param parameter        方法签名
	 * @param mavContainer     上下文容器
	 * @param nativeWebRequest 上下文
	 * @throws Exception 处理异常
	 */
	@Override
	public void handleReturnValue(Object o, MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest) throws Exception {
		/* check */
		HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
		Assert.state(response != null, "No HttpServletResponse");
		ResponseExcel responseExcel = parameter.getMethodAnnotation(ResponseExcel.class);
		Assert.state(responseExcel != null, "No @ResponseExcel");
		mavContainer.setRequestHandled(true);
		responseSheetWriteHandler.export(o,response,responseExcel);
	}
}
