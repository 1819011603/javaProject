package 代理模式.生成代理对象;

import sun.misc.ProxyGenerator;
import 代理模式.增强方法.AppleHandler;
import 代理模式.实现代理模式.MyProxy;
import 代理模式.被代理对象.Apple;
import 代理模式.被代理对象.RedApple;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author 18190
 * @Date: 2023/1/10  11:47
 * @VERSION 1.0
 */
public class Main {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        /*
        Proxy.apply中
                byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);

                defineClass0(loader, proxyName,
                                    proxyClassFile, 0, proxyClassFile.length);
         */

//        第一种保存.class
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Apple apple =(Apple) Proxy.newProxyInstance(RedApple.class.getClassLoader(), RedApple.class.getInterfaces(), new AppleHandler(new RedApple()));
        apple.eat();


//        自己实现的代理对象
        Apple apple1 = (Apple) MyProxy.newInstance(RedApple.class.getClassLoader(),RedApple.class.getInterfaces(),new AppleHandler(new RedApple()),true);
        apple.eat();
        System.out.println(apple.getClass().getSimpleName());
        System.out.println(apple1.getClass().getSimpleName());

        //        第二种保存.class
        MyProxy.saveClassFile(apple.getClass(),apple.getClass().getSimpleName());


    }


}
