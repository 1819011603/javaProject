package 命令模式.命令.具体实现类;

import 命令模式.命令实际运行.Receiver;
import 命令模式.命令实际运行.具体被命令的东西.WashMachine;

/**
 * @author 18190
 * @Date: 2023/1/9  22:18
 * @VERSION 1.0
 */
public class WashMachineCommand implements Command{
    boolean on = true;
    Receiver washMachine;
    public WashMachineCommand(WashMachine washMachine){
        this.washMachine = washMachine;
    }


    @Override
    public void execute() {
        if (on){
            washMachine.on();
        }else {
            washMachine.off();
        }
        on = !on;
    }

    @Override
    public void undo() {
        washMachine.undo();
    }
}
