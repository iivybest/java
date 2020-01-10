package edu.hit.topic.regexp.demo.codesum;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * BeanGeneralToStringMethod
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年12月22日 下午4:05:08
 */
public class BeanGeneralToStringMethod {

    public static <T> String beanToString(T bean) {
        if (bean == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("[");
        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();

            Object value;
            for (int i = 0; i < pds.length; i++) {
                PropertyDescriptor pd = pds[i];
                if (!"class".equals(pd.getName())) {
                    value = pd.getReadMethod().invoke(bean, new Object[0]);
                    sb.append(pd.getName()).append(":").append(value != null ? value : "").append(",");
                }
            }
            sb.append("]");
        } catch (IntrospectionException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        String temp = sb.toString();
        String str = "";
        if (temp.endsWith(",]")) {
            str = temp.replace(",]", "]");
        }
        return str;
    }
}
