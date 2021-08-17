package me.zrxjava.common.utils.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import me.zrxjava.common.exception.ExcelException;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 读 读取单元格，表头，数据转换等
 * 写 导出指定列，复杂头，多次写，格式等
 * @link https://www.yuque.com/easyexcel/doc/r
 * 文件导入导出工具类
 * @author void
 */
public class ExcelUtil {


    /**
     * 默认读取 Excel(第一个sheet)
     *
     * @param excel    文件
     * @param clazz 实体类映射
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T>  clazz) throws IOException {
        return readExcel(excel, clazz, 0, 1);
    }

    /**
     * 读取 全部sheet
     *
     * @param excel    文件
     * @param clazz 实体类映射
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcelAllSheet(MultipartFile excel, Class<T>  clazz) throws IOException {
        ExcelListener<T> excelListener = new ExcelListener<>();
        // doRead方法会处理流的关闭
        EasyExcel.read(excel.getInputStream(), clazz, excelListener).doReadAll();
        return excelListener.getDataList();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param clazz 实体类映射
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel,  Class<T>  clazz, int sheetNo) throws IOException {
        return readExcel(excel, clazz, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param clazz    实体类映射
     * @param sheetNo     sheet 的序号 从0开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T>  clazz, int sheetNo, int headLineNum) throws IOException {

        if (null == excel){
            throw new ExcelException("文件不能为空");
        }
        String fileName = excel.getOriginalFilename();
        Assert.state(fileName != null,"no fileName");
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new ExcelException("文件格式错误！");
        }
        ExcelListener<T> excelListener = new ExcelListener<>();
        // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据clazz 来解析，他没有指定头，也就是默认1行
        // doRead方法会处理流的关闭
        EasyExcel.read(excel.getInputStream(), clazz, excelListener).headRowNumber(headLineNum).sheet(sheetNo).doRead();
        return excelListener.getDataList();
    }


    /**
     * 导出 单个sheet
     * @param list 需要导出的数据
     * @param fileName 导出文件名称
     */
    public static void  writeExcel(HttpServletResponse response,List list, String fileName) throws IOException {
        writeExcel(response,list,fileName,null);
    }

    /**
     * 根据模板写入
     *
     * @param response  HttpServletResponse
     * @param list      数据 list
     * @param fileName  导出的文件名
     * @param templateFileNameWithPath 模板全路径
     */
    public static void  writeExcel(HttpServletResponse response, List list, String fileName,String templateFileNameWithPath) throws IOException {
        Class clazz = list.get(0).getClass();
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // doWrite 方法会处理流的关闭
        if (null != templateFileNameWithPath){
            EasyExcel.write(response.getOutputStream(), clazz).withTemplate(templateFileNameWithPath).sheet().doWrite(list);
        }else {
            EasyExcel.write(response.getOutputStream(), clazz).sheet().doWrite(list);
        }

    }

    /**
     * 下载指定模板
     * @param response
     * @param templatePath 下载模板路径
     * @param fileName 模板文件名称（包含后缀）
     * @throws IOException
     */
    public static void  downTemplate(HttpServletResponse response,String templatePath,String fileName) throws IOException {
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
        // doWrite 方法会处理流的关闭
        EasyExcel.write(response.getOutputStream()).withTemplate(templatePath + File.separator + fileName).sheet().doWrite(new ArrayList());

    }

}
