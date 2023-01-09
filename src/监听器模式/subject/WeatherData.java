package 监听器模式.subject;

import 监听器模式.observe.Observer;
import 监听器模式.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 18190
 * @Date: 2023/1/7  20:18
 * @VERSION 1.0
 */
public class WeatherData implements Subject {

//    Subject向Observer发送的数据
    Object state;

//    保存Observer组
    List<Observer> list = new ArrayList<>();

//    保存Observe对于的update的参数,此次并未实现

    List<Observer> args = new ArrayList<>();


//    控制是否通知Observer组  true表示通知
//    功能就是可以控制通知的时间间隔， 比如： 0.5s更新一次，
    boolean change = true;

    public WeatherData(){
        state = new State();
    }

    WeatherData(Object state){
        this.state = state;
    }

    public void setState(Object state) {
        this.state = state;
        measurementsChanged();
    }

    @Override
    public void registerObserver(Observer o) {
        if(!list.contains(o)){
            list.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
            list.remove(o);

    }

    @Override
    public void notifyObservers() {

        if(change){
            for(Observer o: list){
//            o.update(getState());
                o.update(this,getState());
            }
        }

        change = false;

    }


    public void setChange() {
        this.change = true;
    }

    public Object getState() {
        return state;
    }

    void measurementsChanged(){
        notifyObservers();
    }
}
