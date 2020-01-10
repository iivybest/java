package edu.hit.adv.introspector;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>BeanIntrospector</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年11月9日 - 下午10:28:53
 */
public class BeanIntrospector {
    public static <T> void bean(T bean) {
        try {
            BeanInfo bif = Introspector.getBeanInfo(bean.getClass());

            BeanDescriptor bd = bif.getBeanDescriptor();
            System.out.println(bd.getBeanClass().getName());
            System.out.println(bd.getCustomizerClass());
            System.out.println(bd.toString());
            System.out.println("-------------------------------------");
            PropertyDescriptor[] pds = bif.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String property = pd.getName();
                if (!"class".equals(property)) {
                    Object val = pd.getReadMethod().invoke(bean, new Object[0]);
                    System.out.println(property + " : " + val);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ClubEntity club = new ClubEntity();
        club.setId("00001");
        club.setName("AC Milan");
        club.setAddress("MiLan Italy");
        bean(club);
    }
}
