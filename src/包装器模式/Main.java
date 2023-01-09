package 包装器模式;

import 包装器模式.被装饰者.Beverage;
import 包装器模式.被装饰者.impl.Coffe;
import 包装器模式.装饰者.配料.Decas;
import 包装器模式.装饰者.配料.Milk;

/**
 * @author 18190
 * @Date: 2023/1/8  15:45
 * @VERSION 1.0
 */
public class Main {

    public static void main(String[] args) {
//        一个咖啡   + 1个牛奶 +  1个摩卡
        Beverage my饮料 = new Decas(new Milk(new Coffe()));
        System.out.println(my饮料.getDecription());
        System.out.println(my饮料.cost());

        //        一个咖啡   + 2个牛奶 +  1个摩卡
        my饮料 = new Decas(new Milk(new Milk(new Coffe())));
        System.out.println(my饮料.getDecription());
        System.out.println(my饮料.cost());

    }
}
