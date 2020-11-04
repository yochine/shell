package me.zrxjava.common.utils;



import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 记录对象修改前后的工具类
 * @author void
 * @create 2020-11-04
 */
public class BeanChangeUtils {


    /**
     * 默认对比全部字段
     * @param oldBean
     * @param newBean
     * @return
     */
    public static String contrastObj(Object oldBean, Object newBean){
        return contrastObj(oldBean,newBean,null);
    }

    /**
     * 对比
     * @param oldBean 修改前的对象
     * @param newBean 修改后的对象
     * @param comparedProperties key ：需要对比的属性 value ：记录输出的字段别名
     * @return
     */
    public static String contrastObj(Object oldBean, Object newBean, Map<String,String> comparedProperties) {
        StringBuilder str = new StringBuilder();
        try {
            // 通过反射获取类的类类型及字段属性
            Class clazz = oldBean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                String fieldName = null == comparedProperties ? field.getName() : comparedProperties.get(field.getName());
                // 排除序列化属性
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                //获取属性对应的注解

                if(null == comparedProperties || comparedProperties.containsKey(fieldName)) {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    // 获取对应属性值
                    Method getMethod = pd.getReadMethod();
                    Object o1 = getMethod.invoke(oldBean);
                    Object o2 = getMethod.invoke(newBean);
                    if (o1 == null || o2 == null) {
                        continue;
                    }
                    if (!o1.toString().equals(o2.toString())) {
                        str.append(i).append("、将").append(fieldName).append(":").append(o1).append("修改为:").append(o2).append(";");
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }


}
