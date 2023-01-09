package 工厂模式.工厂方法模式.工厂;

import 工厂模式.工厂方法模式.对象.Pizza;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 18190
 * @Date: 2023/1/8  21:09
 * @VERSION 1.0
 */
public abstract class AbstractPizzaFactor {
    public abstract Pizza createPizza(String pizzaType);



}