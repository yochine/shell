package me.zrxjava.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式转换器
 * 不要听信idea的自动提示将代码转化成lambda方式，会报错
 * @author void
 * @create 2020-11-08
 */
@Configuration
public class DateConverterConfig {

    private static final Logger logger = LoggerFactory.getLogger(DateConverterConfig.class);

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = null;
                try {
                    date = LocalDateTime.parse(source, df);
                } catch (Exception e) {
                    logger.error("转换LocalDateTime出错：{}",source);
                    e.printStackTrace();
                }
                return date;
            }
        };
    }


    @Bean
    public Converter<String, LocalDate> localDateConvert() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = null;
                try {
                    date = LocalDate.parse(source, df);
                } catch (Exception e) {
                    logger.error("转换LocalDate出错：{}",source);
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalTime> localTimeConvert() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime date = null;
                try {
                    date = LocalTime.parse(source, df);
                } catch (Exception e) {
                    logger.error("转换LocalTime出错：{}",source);
                    e.printStackTrace();
                }
                return date;
            }
        };
    }


}
