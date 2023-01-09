package 工厂模式.简单工厂模式;

import 工厂模式.简单工厂模式.对象.Pizza;
import 工厂模式.简单工厂模式.工厂.SimpleFactory;

/**
 * @author 18190
 * @Date: 2023/1/8  21:05
 * @VERSION 1.0
 *
 * 订单系统
 */
public class OrderPizza {



    Pizza orderPizza(String pizzaType) {
        Pizza pizza = SimpleFactory.createPizza(pizzaType);
        System.out.println("当前pizza名字："+pizza.getName());
        //准备材料
        pizza.prepare();
        //烘烤
        pizza.bake();
        //切
        pizza.cut();
        //装盒
        pizza.box();
        return pizza;
    }

    public static void main(String[] args) {
        new OrderPizza().orderPizza("greek");
        new OrderPizza().orderPizza("chess");
        new OrderPizza().orderPizza("pepper");
    }


}
