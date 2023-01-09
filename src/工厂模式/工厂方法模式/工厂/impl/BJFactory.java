package 工厂模式.工厂方法模式.工厂.impl;

import 工厂模式.工厂方法模式.对象.Pizza;
import 工厂模式.工厂方法模式.对象.impl.BJChessPizza;
import 工厂模式.工厂方法模式.对象.impl.BJGreekPizza;
import 工厂模式.工厂方法模式.对象.impl.BJPepperPizza;
import 工厂模式.工厂方法模式.工厂.AbstractPizzaFactor;

/**
 * @author 18190
 * @Date: 2023/1/8  21:21
 * @VERSION 1.0
 */
public class BJFactory extends AbstractPizzaFactor {
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

}
