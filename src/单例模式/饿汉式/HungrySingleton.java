package 单例模式.饿汉式;

import 单例模式.ReflectTest;
import 单例模式.Singleton;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * @author 18190
 * @Date: 2023/1/9  15:37
 * @VERSION 1.0
 */
public class HungrySingleton implements Serializable, Singleton<HungrySingleton> {

    private static final long serialVersionUID = -13442156L;

    private HungrySingleton() throws Exception {
//        保证单例   但是这样保证单例难免繁琐
//        if (hungrySingleton != null){
//            throw new Exception("不能破坏单例");
//        }
    }
    private static HungrySingleton hungrySingleton = null;

    static {
        try {
            hungrySingleton = new HungrySingleton();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public  HungrySingleton getSingleton(){
        return hungrySingleton;
    }

    public static void main(String[] args) {
        ReflectTest<HungrySingleton> reflectTest = new ReflectTest<>();
        reflectTest.testReflect(HungrySingleton.class);
        reflectTest.testSerialization(HungrySingleton.class,"hungrySingleton");
    }



    //        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
    //    desc.hasReadResolveMethod
    //    Object rep = desc.invokeReadResolve(obj);  调用该方法
    public Object readResolve(){
        return HungrySingleton.hungrySingleton;
    }
}
