package 单例模式.枚举类;

import 单例模式.ReflectTest;
import 单例模式.Singleton;
import 单例模式.双重检查锁.DoubleCheckSingleton;

import java.io.Serializable;

/**
 * @author 18190
 * @Date: 2023/1/9  17:41
 * @VERSION 1.0
 */
public class EnumSingleton implements Serializable, Singleton<EnumSingleton> {
    private EnumSingleton(){}

    @Override
    public EnumSingleton getSingleton() {
        return Enum.INSTANCE.data;
    }

    private enum Enum{
        INSTANCE;
        private EnumSingleton data;
        private Enum(){
            data = new EnumSingleton();
        }



    }

    public static void main(String[] args) {
//     内部Enum   无法防止序列化破坏
        Class<EnumSingleton> lazySingletonClass = EnumSingleton.class;
        String propertyName = "doubleCheckSingleton";

        ReflectTest<EnumSingleton> reflectTest = new ReflectTest<>();
        reflectTest.testManyThread(lazySingletonClass);
        reflectTest.testReflect(lazySingletonClass);

//        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
//        false
        reflectTest.testSerialization(lazySingletonClass,propertyName);
    }

    //        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
    //    desc.hasReadResolveMethod
    //    Object rep = desc.invokeReadResolve(obj);  调用该方法
//    public Object readResolve(){
//        return Enum.INSTANCE.data;
//    }
}
