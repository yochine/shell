package me.zrxjava.common.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import me.zrxjava.common.annotation.ResponseExcel;
import me.zrxjava.common.converter.LocalDateStringConverter;
import me.zrxjava.common.converter.LocalDateTimeStringConverter;
import me.zrxjava.common.exception.ExcelException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author void
 * @create 2020-11-03
 */
public class ResponseSheetWriteHandler {

    @Value("${easyexcel.templatePath}")
    private String templatePath;

    public void check(ResponseExcel responseExcel) {
        if (!StringUtils.hasText(responseExcel.name())) {
            throw new ExcelException("@ResponseExcel name 配置不合法");
        }

        if (responseExcel.sheet().length == 0) {
            throw new ExcelException("@ResponseExcel sheet 配置不合法");
        }
    }

    public ExcelWriter getExcelWriter(HttpServletResponse response, ResponseExcel responseExcel, List list) throws IOException {
        // 数据类型
        Class<?> dataClass = list.get(0).getClass();

        ExcelWriterBuilder writerBuilder = EasyExcel.write(response.getOutputStream(), dataClass)
                .registerConverter(LocalDateStringConverter.INSTANCE)
                .registerConverter(LocalDateTimeStringConverter.INSTANCE)
                .autoCloseStream(true)
                .excelType(responseExcel.suffix())
                .inMemory(responseExcel.inMemory());

        if (StringUtils.hasText(responseExcel.password())) {
            writerBuilder.password(responseExcel.password());
        }

        if (responseExcel.include().length != 0) {
            writerBuilder.includeColumnFiledNames(Arrays.asList(responseExcel.include()));
        }

        if (responseExcel.exclude().length != 0) {
            writerBuilder.excludeColumnFiledNames(Arrays.asList(responseExcel.include()));
        }

        if (responseExcel.writeHandler().length != 0) {
            for (Class<? extends WriteHandler> clazz : responseExcel.writeHandler()) {
                writerBuilder.registerWriteHandler(BeanUtils.instantiateClass(clazz));
            }
        }

        if (responseExcel.converter().length != 0) {
            for (Class<? extends Converter> clazz : responseExcel.converter()) {
                writerBuilder.registerConverter(BeanUtils.instantiateClass(clazz));
            }
        }

        if (StringUtils.hasText(responseExcel.template())) {
            ClassPathResource classPathResource = new ClassPathResource(templatePath + File.separator + responseExcel.template());
            InputStream inputStream = classPathResource.getInputStream();
            writerBuilder.withTemplate(inputStream);
        }

        return writerBuilder.build();
    }



    public void export(Object o, HttpServletResponse response, ResponseExcel responseExcel) throws IOException {

        check(responseExcel);
        String fileName = String.format("%s%s", URLEncoder.encode(responseExcel.name(), "UTF-8"), responseExcel.suffix().getValue());
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        write(o, response, responseExcel);
    }

    public void write(Object obj, HttpServletResponse response, ResponseExcel responseExcel) throws IOException {
        List objList = (List) obj;
        // 返回类型是否为多list
        boolean isSingle = objList.get(0) instanceof List;
        List eleList = !isSingle ? (List) objList.get(0) : objList;

        ExcelWriter excelWriter = getExcelWriter(response, responseExcel, eleList);

        String[] sheets = responseExcel.sheet();
        for (int i = 0; i < sheets.length; i++) {
            //创建sheet
            WriteSheet sheet;
            if (StringUtils.hasText(responseExcel.template())) {
                sheet = EasyExcel.writerSheet(i).build();
            } else {
                sheet = EasyExcel.writerSheet(i, sheets[i]).build();
            }
            if (isSingle){
                excelWriter.write(objList, sheet);
            }else {
                excelWriter.write((List) objList.get(i), sheet);
            }
        }
        excelWriter.finish();
    }
}
