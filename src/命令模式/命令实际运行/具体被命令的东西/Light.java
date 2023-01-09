package 命令模式.命令实际运行.具体被命令的东西;

import 命令模式.命令实际运行.Receiver;

/**
 * @author 18190
 * @Date: 2023/1/9  22:16
 * @VERSION 1.0
 */
public class Light implements Receiver {
    @Override
    public void on() {
        System.out.println("light on");
    }

    @Override
    public void off() {
        System.out.println("light off");
    }

    @Override
    public void undo() {
        System.out.println("light undo");
    }
}
