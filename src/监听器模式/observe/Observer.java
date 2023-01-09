package 监听器模式.observe;

import 监听器模式.state.State;
import 监听器模式.subject.Subject;

/**
 * @author 18190
 * @Date: 2023/1/7  20:16
 * @VERSION 1.0
 */
public interface Observer {

//    使用Object  实现类可以根据需要向子类转化
    void update(Object o);

    /**
     *
     * @param subject  根据主题对象更改更新措施
     * @param args  参数
     */
    default void update(Subject subject, Object args) {

    }
}
