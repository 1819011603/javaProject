package 单例模式.枚举类;

import 单例模式.ReflectTest;
import 单例模式.Singleton;
import 单例模式.懒汉式.LazySingleton;

import java.io.Serializable;

/**
 * @author 18190
 * @Date: 2023/1/9  17:41
 * @VERSION 1.0
 */

@SuppressWarnings("all")
public enum NotSerializableEnumSingleton implements Serializable,Singleton<NotSerializableEnumSingleton> {
    Instance;
    Object notSerializableEnumSingleton;

    private NotSerializableEnumSingleton(){
    }

    public void setNotSerializableEnumSingleton(Object notSerializableEnumSingleton) {
        Instance.notSerializableEnumSingleton = notSerializableEnumSingleton;
    }

    public static void main(String[] args) {

//        枚举类是无法用反射创建  https://blog.csdn.net/qq_42672175/article/details/109531075
        NotSerializableEnumSingleton instance = NotSerializableEnumSingleton.Instance;
        instance.setNotSerializableEnumSingleton(new Object[10]);

        Class<NotSerializableEnumSingleton> lazySingletonClass = NotSerializableEnumSingleton.class;
        String propertyName = "doubleCheckSingleton";


        ReflectTest<NotSerializableEnumSingleton> reflectTest = new ReflectTest<>(instance);
        reflectTest.testManyThread(lazySingletonClass);
        reflectTest.testReflect(lazySingletonClass);

//        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
//        false
        reflectTest.testSerialization(lazySingletonClass,propertyName);

    }

    @Override
    public NotSerializableEnumSingleton getSingleton() {
        return Instance;
    }


    //        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
    //    desc.hasReadResolveMethod
    //    Object rep = desc.invokeReadResolve(obj);  调用该方法
//    public Object readResolve(){
//        return Enum.INSTANCE.data;
//    }
}
