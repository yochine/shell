package me.zrxjava.common.utils.easyexcel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import me.zrxjava.common.exception.ExcelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author void
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);


    private List<T> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(T object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        dataList.add(object);
        //根据业务自行 do something
        doSomething();

        /*
        如数据过大，可以进行定量分批处理
        if(datas.size()<=100){
            datas.add(object);
        }else {
            doSomething();
            datas = new ArrayList<Object>();
        }
         */

    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        /*
            datas.clear();
            解析结束销毁不用的资源
         */
        int size = dataList.size();
        if (size <= 0) {
            logger.error("导入文件为空或第一行数据为空");
            throw new ExcelException("导入文件为空或第一行数据为空");
        }
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}