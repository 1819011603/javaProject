package 工厂模式.抽象工厂模式.工厂.impl;

import 工厂模式.抽象工厂模式.对象.Car;
import 工厂模式.抽象工厂模式.对象.Car实现类.BJACar;
import 工厂模式.抽象工厂模式.对象.Car实现类.BJCar;
import 工厂模式.抽象工厂模式.对象.Pizza;
import 工厂模式.抽象工厂模式.对象.impl.BJChessPizza;
import 工厂模式.抽象工厂模式.对象.impl.BJGreekPizza;
import 工厂模式.抽象工厂模式.对象.impl.BJPepperPizza;
import 工厂模式.抽象工厂模式.工厂.AbstractFactor;


/**
 * @author 18190
 * @Date: 2023/1/8  21:21
 * @VERSION 1.0
 */
public class BJFactory extends AbstractFactor {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("chess")){
            pizza = new BJChessPizza();
        }else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }else if(orderType.equals("greek")){
            pizza = new BJGreekPizza();
        }
        pizza.setName("BJ" + orderType);

        return pizza;
    }

    @Override
    public Car createCar(String carType) {
        Car car = null;
        if (carType .equals("")){
            car =  new BJCar();
        }else if ("A".equals(carType)){
            car =  new BJACar();
        }
        car.setName("BJ"+carType+"car");
        return car;
    }

}
