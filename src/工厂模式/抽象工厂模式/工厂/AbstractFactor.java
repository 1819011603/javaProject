package 工厂模式.抽象工厂模式.工厂;


import 工厂模式.抽象工厂模式.对象.Car;
import 工厂模式.抽象工厂模式.对象.Pizza;

/**
 * @author 18190
 * @Date: 2023/1/8  21:09
 * @VERSION 1.0
 */
public abstract class AbstractFactor {

//    工厂方法 该抽象类只创建一种大类 就是Pizza
    public abstract Pizza createPizza(String pizzaType);

//    抽象工厂  该抽象类创建多种大类 Pizza、Car 等等
    public abstract Car createCar(String carType);



}