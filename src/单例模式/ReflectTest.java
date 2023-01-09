package 单例模式;

import com.sun.xml.internal.ws.api.pipe.Engine;
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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 30, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),new DaemonThreadFactory());
        for (int i = 0; i < total; i++){
            int finalI = i;
            threadPoolExecutor.submit(()->{
                list1.add(list.get(finalI).getSingleton());
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        list1.sort((a,b)->a.hashCode()-b.hashCode());

//        System.out.println(list1);
        System.out.println("testManyThread: " + list1.get(0).equals(list1.get(list1.size() - 1)));

//        要关闭线程池  不然程序不会立马停止
//        使用线程池时，在main完成之前没的调用shutdonw使得，
//        java进程不会结束。线程池默认的线程不是“守护线程”，
//        线程池的timeout 大于 0时，code数量的线程是不会终止的。所以，当所有任务完成后，java程序不会结束。
//        threadPoolExecutor.shutdown();
    }


    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

    }


    private static class DaemonThreadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        DaemonThreadFactory() {
            this.namePrefix = "myZzl-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread((ThreadGroup)null, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (!t.isDaemon()) {
                t.setDaemon(true);
            }

            if (t.getPriority() != 5) {
                t.setPriority(5);
            }

            return t;
        }
    }
}
