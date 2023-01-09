package 单例模式.静态内部类;

import 单例模式.ReflectTest;
import 单例模式.Singleton;
import 单例模式.双重检查锁.DoubleCheckSingleton;

import java.io.Serializable;

/**
 * @author 18190
 * @Date: 2023/1/9  17:22
 * @VERSION 1.0
 */
public class LazyStaticInnerSingleton implements Serializable, Singleton<LazyStaticInnerSingleton> {
//    private static final long serialVersionUID = 422424L;
    private LazyStaticInnerSingleton(){}
    private static class  StaticInner{
        private static final LazyStaticInnerSingleton lazyStaticInnerSingleton = new LazyStaticInnerSingleton();
    }
    @Override
    public LazyStaticInnerSingleton getSingleton() {
        return StaticInner.lazyStaticInnerSingleton;
    }

    public static void main(String[] args) {
        Class<LazyStaticInnerSingleton> lazySingletonClass = LazyStaticInnerSingleton.class;
        String propertyName = "lazyStaticInnerSingleton";

        ReflectTest<LazyStaticInnerSingleton> reflectTest = new ReflectTest<>();
        reflectTest.testManyThread(lazySingletonClass);
        reflectTest.testReflect(lazySingletonClass);
        reflectTest.testSerialization(lazySingletonClass,propertyName);
    }


    //        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
    //    desc.hasReadResolveMethod
    //    Object rep = desc.invokeReadResolve(obj);  调用该方法
    public Object readResolve(){
        return StaticInner.lazyStaticInnerSingleton;
    }
}
