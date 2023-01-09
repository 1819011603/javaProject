package 工厂模式.抽象工厂模式.工厂.impl;

import 工厂模式.抽象工厂模式.对象.Car;
import 工厂模式.抽象工厂模式.对象.Car实现类.BJACar;
import 工厂模式.抽象工厂模式.对象.Car实现类.BJCar;
import 工厂模式.抽象工厂模式.对象.Car实现类.LDACar;
import 工厂模式.抽象工厂模式.对象.Car实现类.LDCar;
import 工厂模式.抽象工厂模式.对象.Pizza;
import 工厂模式.抽象工厂模式.对象.impl.LDChessPizza;
import 工厂模式.抽象工厂模式.对象.impl.LDGreekPizza;
import 工厂模式.抽象工厂模式.对象.impl.LDPepperPizza;
import 工厂模式.抽象工厂模式.工厂.AbstractFactor;


/**
 * @author 18190
 * @Date: 2023/1/8  21:21
 * @VERSION 1.0
 */
public class LDFactory extends AbstractFactor {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("chess")){
            pizza = new LDChessPizza();
        }else if (orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }else if(orderType.equals("greek")){
            pizza = new LDGreekPizza();
        }
        pizza.setName("LD"+orderType);
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
        car.setName("LD"+carType+"car");
        return car;
    }

}
