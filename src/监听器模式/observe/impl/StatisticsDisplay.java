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
public class StatisticsDisplay implements Observer, DisplayElement {
    Object o;
    double min = Integer.MAX_VALUE,max = 0,sum = 0,time = 0;

    Subject s;
    public StatisticsDisplay(Subject s){
        this.s = s;
        register();
    }
    @Override
    public void display() {
        State s = (State) o;
        System.out.println("Avg/Min/Max temperature =  " + (String.format("%.1f",(((double)sum)/ time)))+ "/" +String.format("%.1f",min) + "/" +String.format("%.1f",max));
    }

    @Override
    public void update(Object o) {
        this.o = o;
        State s = (State) o;

        min = Math.min(min,s.getTemperature());
        max = Math.max(max,s.getTemperature());

        sum += s.getTemperature();
        ++time;
        display();
    }

    @Override
    public void update(Subject subject, Object args) {
        if (subject instanceof WeatherData){
            this.o = args;
            State s = (State) o;

            min = Math.min(min,s.getTemperature());
            max = Math.max(max,s.getTemperature());

            sum += s.getTemperature();
            ++time;
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
