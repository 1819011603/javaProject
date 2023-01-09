package 监听器模式.observe.impl;

import 监听器模式.display.DisplayElement;
import 监听器模式.observe.Observer;
import 监听器模式.state.State;
import 监听器模式.subject.Subject;
import 监听器模式.subject.WeatherData;

/**
 * @author 18190
 * @Date: 2023/1/7  20:26
 * @VERSION 1.0
 */
public class ForestDisplay implements Observer, DisplayElement {
    Object o;
    Subject s;
    public ForestDisplay(Subject s){
        this.s = s;
        register();
    }
    float cur = -1;
    @Override
    public void display() {
        State s = (State) o;

        if(cur == -1){
            System.out.println("Forecast: improve weather on the way");
        }else{

            if (s.getPressure() < cur ){
                System.out.println("Forecast: watch out for cooler,rainy weather");
            }else if(s.getPressure() == cur){
                System.out.println("Forecast: more of same");
            }else{
                System.out.println("Forecast: improve weather on the way");
            }

        }
        cur = s.getPressure();
    }

    @Override
    public void update(Object o) {

        this.o = o;

        display();
    }

    @Override
    public void update(Subject subject, Object args) {
        if (subject instanceof WeatherData){
            this.o = args;

            display();
        }
    }

    public void register(){
        s.registerObserver(this);
    }

    public void remove(){
        s.removeObserver(this);
    }
}
