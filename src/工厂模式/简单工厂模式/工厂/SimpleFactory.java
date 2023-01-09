package 工厂模式.简单工厂模式.工厂;

import 工厂模式.简单工厂模式.对象.Pizza;
import 工厂模式.简单工厂模式.对象.impl.ChessPizza;
import 工厂模式.简单工厂模式.对象.impl.GreekPizza;
import 工厂模式.简单工厂模式.对象.impl.PepperPizza;

/**
 * @author 18190
 * @Date: 2023/1/8  21:09
 * @VERSION 1.0
 */
public class SimpleFactory {
    public static Pizza createPizza(String pizzaType) {
        Pizza pizza = null;
        System.out.println("使用了简单工厂模式");
        if (pizzaType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName("greek");
        } else if (pizzaType.equals("chess")) {
            pizza = new ChessPizza();
            pizza.setName("chess");
        } else if (pizzaType.equals("pepper")) {//新增PepperPizza的时候 修改了源代码 违反了ocp原则 如果新增10000个？
            //那就很麻烦了
            pizza = new PepperPizza();
            pizza.setName("pepper");
        }

        return pizza;
    }

}