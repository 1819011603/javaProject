package 单例模式.懒汉式;

import 单例模式.ReflectTest;
import 单例模式.Singleton;
import 单例模式.饿汉式.HungrySingleton;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 18190
 * @Date: 2023/1/9  16:24
 * @VERSION 1.0
 */
public class LazySingleton  implements Serializable, Singleton<LazySingleton> {
    static volatile AtomicInteger i =new AtomicInteger(0);
    private LazySingleton(){

    }
    private static  LazySingleton lazySingleton = null;
    @Override
    public LazySingleton getSingleton() {

        if (lazySingleton == null){

            try {
                if (i.get() == 0){
//                    保证更多线程进来这个if
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lazySingleton = new LazySingleton();

        }
        i.incrementAndGet();
        return lazySingleton;
    }
    public static void main(String[] args) {

        ReflectTest<LazySingleton> reflectTest = new ReflectTest<>();
        reflectTest.testManyThread(LazySingleton.class);
        reflectTest.testReflect(LazySingleton.class);
        reflectTest.testSerialization(LazySingleton.class,"lazySingleton");
    }


    //        readOrdinaryObject方法中通过desc.newInstance() 创建新对象
    //    desc.hasReadResolveMethod
    //    Object rep = desc.invokeReadResolve(obj);  调用该方法
    public Object readResolve(){
        return lazySingleton;
    }
}
