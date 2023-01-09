package 工厂模式.抽象工厂模式;

import 工厂模式.抽象工厂模式.对象.Car;
import 工厂模式.抽象工厂模式.对象.Pizza;
import 工厂模式.抽象工厂模式.工厂.AbstractFactor;

import 工厂模式.抽象工厂模式.工厂.impl.BJFactory;
import 工厂模式.抽象工厂模式.工厂.impl.LDFactory;

/**
 * @author 18190
 * @Date: 2023/1/8  21:05
 * @VERSION 1.0
 *
 * 订单系统
 */
public class OrderAll {
    AbstractFactor factor;
    public OrderAll(AbstractFactor factor){
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

    Car  orderCar(String carType){
        Car car = factor.createCar(carType);
        System.out.println(car.getName());
        return car;
    }

    public static void main(String[] args) {
        AbstractFactor factor1 = new BJFactory();
        AbstractFactor factor2 = new LDFactory();

        new OrderAll(factor1).orderPizza("greek");
        new OrderAll(factor1).orderPizza("chess");
        new OrderAll(factor1).orderPizza("pepper");



        new OrderAll(factor2).orderPizza("greek");
        new OrderAll(factor2).orderPizza("chess");
        new OrderAll(factor2).orderPizza("pepper");
        System.out.println("------------------------------------");
        System.out.println();
        new OrderAll(factor1).orderCar("");
        new OrderAll(factor1).orderCar("A");

        new OrderAll(factor2).orderCar("");
        new OrderAll(factor2).orderCar("A");
    }


}
