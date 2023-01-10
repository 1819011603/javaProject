package 代理模式.被代理对象;

/**
 * @author 18190
 * @Date: 2023/1/10  11:43
 * @VERSION 1.0
 */
public class RedApple implements Apple {
    @Override
    public void eat() {
        System.out.println("吃红苹果");
    }
}
