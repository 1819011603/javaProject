package 代理模式.增强方法;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 18190
 * @Date: 2023/1/10  11:43
 * @VERSION 1.0
 */
public class AppleHandler implements InvocationHandler {


    Object orignal;

    public AppleHandler(Object o){
        orignal = o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("洗苹果");

        Object invoke = method.invoke(orignal, args);
        System.out.println("丢苹果核");
        return invoke;
    }
}
