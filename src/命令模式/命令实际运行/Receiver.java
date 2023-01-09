package 命令模式.命令实际运行;

/**
 * @author 18190
 * @Date: 2023/1/9  22:14
 * @VERSION 1.0
 */
public interface Receiver {
    public  void on();
    public  void off();
    public  void undo();
}
