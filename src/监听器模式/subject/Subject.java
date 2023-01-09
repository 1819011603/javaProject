package 监听器模式.subject;

import 监听器模式.observe.Observer;

/**
 * @author 18190
 * @Date: 2023/1/7  20:15
 * @VERSION 1.0
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    void notifyObservers();
}
