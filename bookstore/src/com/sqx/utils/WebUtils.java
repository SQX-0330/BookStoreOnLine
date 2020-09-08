package com.sqx.utils;

import com.sqx.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author SQX
 * @date 2020/9/4 - 20:23
 */
public class WebUtils {
    /**
     *
     * @param value
     * @param bean
     *
     * HttpServletRequest
     *
     * 把Map中的值注入到对应的JavaBean属性中
     * Dao层
     * Service层
     * Web层
     */
    public static  <T>T copyParamToBean(Map value, T bean){
        try {

//            System.out.println("注入之前"+ bean);

            /**
             * 那所有请求参数注入到user对象中
             */
            BeanUtils.populate(bean, value);
//            System.out.println("注入之后"+ bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转化为int
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
