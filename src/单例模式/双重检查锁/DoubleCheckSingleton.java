package 单例模式.双重检查锁;

import 单例模式.ReflectTest;
import 单例模式.Singleton;
import 单例模式.懒汉式.LazySingleton;

import java.io.Serializable;

/**
 * @author 18190
 * @Date: 2023/1/9  17:15
 * @VERSION 1.0
 */
public class DoubleCheckSingleton   implements Serializable, Singleton<DoubleCheckSingleton> {
    private static volatile DoubleCheckSingleton doubleCheckSingleton = null;
    private DoubleCheckSingleton(){

    }
    @Override
    public DoubleCheckSingleton getSingleton() {
        if (doubleCheckSingleton == null){
            synchronized (DoubleCheckSingleton.class){
                if (doubleCheckSingleton == null){
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }

    public static void main(String[] args) {

        Class<DoubleCheckSingleton> lazySingletonClass = DoubleCheckSingleton.class;
        String propertyName = "doubleCheckSingleton";

        ReflectTest<DoubleCheckSingleton> reflectTest = new ReflectTest<>();
        reflectTest.testManyThread(lazySingletonClass);
        reflectTest.testReflect(lazySingletonClass);
        reflectTest.testSerialization(lazySingletonClass,propertyName);
    }

    public Object readResolve(){
        return doubleCheckSingleton;
    }
}
