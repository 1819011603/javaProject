package 单例模式;

import 单例模式.Singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 18190
 * @Date: 2023/1/9  15:42
 * @VERSION 1.0
 */
public class ReflectTest<T> {
//    NotSerializableEnumSingleton  是无法使用反射创建的 只能通过构造函数传递
    Singleton<T> o;

//    保证错误代码只输出一次
    static volatile AtomicInteger i = new AtomicInteger();
    public ReflectTest(){

    }

    public ReflectTest(Singleton<T> o){
        this.o = o;
    }
    //    检测反射
    public  Singleton<T> getInstanceByReflect(Class<? extends Singleton<T>> cl,Class<?> ...args){
        Singleton<T> singleton = null;
        try {
            Constructor<? extends Singleton<T>> declaredConstructor = cl.getDeclaredConstructor(args);

            declaredConstructor.setAccessible(true);

            singleton = declaredConstructor.newInstance("测试",0);
        }catch (Exception e){
            if (i.get() == 0){
//          无法创建的真正原因
                System.out.println("无法创建的真正原因if ((clazz.getModifiers() & Modifier.ENUM) != 0)\n" +
                        "            throw new IllegalArgumentException(\"Cannot reflectively create enum objects\");");
                System.out.println(e.toString());
            }


//            如果无法用反射  直接返回
            return this.o;

        }finally {
            i.incrementAndGet();
        }
        return singleton;
    }
//    检测反射
    public  Singleton<T> getInstanceByReflect(Class<? extends Singleton<T>> cl){
        Singleton<T> singleton = null;

        try {
            Constructor<? extends Singleton<T>> declaredConstructor = cl.getDeclaredConstructor();

            declaredConstructor.setAccessible(true);

            singleton = declaredConstructor.newInstance();
        }catch (Exception e){
//            System.out.println(cl.getSimpleName() + "没有这个构造函数");
//            System.out.println(cl.getSimpleName() + " cl.getDeclaredConstructors()所有构造函数为:" + Arrays.toString(cl.getDeclaredConstructors()));
            singleton = getInstanceByReflect(cl,String.class,int.class);
//            如果无法用反射  直接返回
            return this.o;

        }

        return singleton;
    }

    public void testReflect(Class<? extends Singleton<T>> cl) {
       try {
           i.set(0);
           Singleton<T> singleton = getInstanceByReflect(cl);

//        false
//           实现Singleton接口就只是为可以调用getSingleton方法
           System.out.println("testReflect:" + (singleton.getSingleton() == singleton));
       }catch (Exception e){
           System.out.println(e.toString());
       }
    }


    // 检测序列化
    public void testSerialization(Class<? extends Singleton<T>> cl,String propertyName){
//        为了拿到单例
        Singleton<T> singleton = getInstanceByReflect(cl);
        i.set(0);
//        拿到单例
        T instance = singleton.getSingleton();
        T serializedInstance = null;

        ByteArrayOutputStream outputStream = null;
//        FileOutputStream outputStream = null;
        ObjectOutputStream oos = null;
        try {
            outputStream = new ByteArrayOutputStream();
//            outputStream = new FileOutputStream("D:\\1\\a.txt");
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(instance);
            oos.flush();


            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//            FileInputStream inputStream = new FileInputStream("D:\\1\\a.txt");
            ObjectInputStream inputStream1 = new ObjectInputStream(inputStream);
            serializedInstance = (T)inputStream1.readObject();

            oos.close();
            outputStream.close();
            inputStream.close();
            inputStream1.close();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        System.out.println("testSerialization:"+(serializedInstance == instance && instance!= null));


    }


//    检测多线程  testManyThread必须放在testReflect和testSerialization之前 因为testReflect和testSerialization调用过getSingleton

    public void testManyThread(Class<? extends Singleton<T>> cl){
        List< Singleton<T>> list = new ArrayList<>();

        int total = 50;
        i.set(0);

        for (int i = 0; i < total; i++){
            list.add(getInstanceByReflect(cl));
        }

        List<T> list1 = new CopyOnWriteArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(total);

        for (int i = 0; i < total; i++){
            int finalI = i;
            new Thread(()->{
                list1.add(list.get(finalI).getSingleton());
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        list1.sort((a,b)->a.hashCode()-b.hashCode());

//        System.out.println(list1);
        System.out.println("testManyThread: " + list1.get(0).equals(list1.get(list1.size() - 1)));

    }


    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

    }
}
