package me.zrxjava.system.config;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author void
 * @create 2020-11-01
 */
@Configuration
public class RestTemplateConfig {

    @Value("${rest.connection.timeout}")
    private Integer connectionTimeout;

    @Value("${rest.read.timeout}")
    private Integer readTimeout;

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory());
        //这个地方需要配置消息转换器，不然收到消息后转换会出现异常
        restTemplate.setMessageConverters(getConverts());
        // 加入TraceId
        restTemplate.setInterceptors(Collections.singletonList((httpRequest, bytes, clientHttpRequestExecution) -> {
            String traceId = MDC.get("traceId");
            if (null != traceId) {
                httpRequest.getHeaders().add("traceId", traceId);
            }
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectionTimeout);
        return factory;
    }

    /**
     * 功能描述：  设置数据转换器，这里只设置了String转换器
     * @param
     **/
    private List<HttpMessageConverter<?>> getConverts() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        // String转换器
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            //配置text/plain和text/html类型的数据都转成String
            add(MediaType.TEXT_PLAIN);
            add(MediaType.TEXT_HTML);
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        messageConverters.add(stringConvert);
        return messageConverters;
    }

}
