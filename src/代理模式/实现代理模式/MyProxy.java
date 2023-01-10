package 代理模式.实现代理模式;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author 18190
 * @Date: 2023/1/10  13:34
 * @VERSION 1.0
 */
public class MyProxy {

    /*
        查看Proxy类中内部类ProxyClassFactory的apply方法即可
     */



    public static Object newInstance(ClassLoader loader, Class<?>[] interfaces,
                              InvocationHandler h,boolean saveFlag ){


        try {


//            获得代理对象的类
            Class<?> proxyInstanceClass =  Proxy.getProxyClass(loader,interfaces);
//          本质上是调用ProxyClassFactory.apply方法
            /*
                    byte[] proxyClassFile = ProxyGenerator.generateProxyClass(  proxyName, interfaces, accessFlags);
                   return defineClass0(loader, proxyName,  proxyClassFile, 0, proxyClassFile.length);


                   defineClass0 是不能访问的
             */

//            获得构造器
            Constructor<?> declaredConstructor = proxyInstanceClass.getDeclaredConstructor(InvocationHandler.class);

            declaredConstructor.setAccessible(true);

            return declaredConstructor.newInstance(h);

        } catch (ClassFormatError | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {

            e.printStackTrace();
        }

        return null;
    }

    public static void saveClassFile(Class clazz,String proxyName) {
        //生成class的字节数组，此处生成的class与proxy.newProxyInstance中生成的class除了代理类的名字不同，其它内容完全一致
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String path = "F:\\javaProject\\src\\代理模式\\实现代理模式\\";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(path + proxyName + ".class"));
            fos.write(classFile);//保存到磁盘
            fos.flush();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
