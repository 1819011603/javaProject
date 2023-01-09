package 工厂模式.工厂方法模式;

import 工厂模式.工厂方法模式.对象.Pizza;
import 工厂模式.工厂方法模式.工厂.AbstractPizzaFactor;
import 工厂模式.工厂方法模式.工厂.impl.BJFactory;
import 工厂模式.工厂方法模式.工厂.impl.LDFactory;

/**
 * @author 18190
 * @Date: 2023/1/8  21:05
 * @VERSION 1.0
 *
 * 订单系统
 */
public class OrderPizza {
    AbstractPizzaFactor factor;
    public OrderPizza(AbstractPizzaFactor factor){
        this.factor = factor;

    }

    Pizza orderPizza(String pizzaType) {
        Pizza pizza = factor.createPizza(pizzaType);
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
        AbstractPizzaFactor factor1 = new BJFactory();
        AbstractPizzaFactor factor2 = new LDFactory();

        new OrderPizza(factor1).orderPizza("greek");
        new OrderPizza(factor1).orderPizza("chess");
        new OrderPizza(factor1).orderPizza("pepper");



        new OrderPizza(factor2).orderPizza("greek");
        new OrderPizza(factor2).orderPizza("chess");
        new OrderPizza(factor2).orderPizza("pepper");
    }


}
