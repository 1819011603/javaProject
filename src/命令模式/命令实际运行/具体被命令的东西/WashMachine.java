package 命令模式.命令实际运行.具体被命令的东西;

import 命令模式.命令实际运行.Receiver;

/**
 * @author 18190
 * @Date: 2023/1/9  22:16
 * @VERSION 1.0
 */
public class WashMachine implements Receiver {
    @Override
    public void on() {
        System.out.println("WashMachine on");
    }

    @Override
    public void off() {
        System.out.println("WashMachine off");
    }

    @Override
    public void undo() {
        System.out.println("WashMachine undo");
    }
}
