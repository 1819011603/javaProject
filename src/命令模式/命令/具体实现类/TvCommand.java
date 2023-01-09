package 命令模式.命令.具体实现类;

import 命令模式.命令实际运行.Receiver;
import 命令模式.命令实际运行.具体被命令的东西.Tv;

/**
 * @author 18190
 * @Date: 2023/1/9  22:18
 * @VERSION 1.0
 */
public class TvCommand implements Command{
    boolean on = true;
    Receiver tv;
    public TvCommand(Tv light){
        this.tv = light;
    }


    @Override
    public void execute() {
        if (on){
            tv.on();
        }else {
            tv.off();
        }
        on = !on;
    }

    @Override
    public void undo() {
        tv.undo();
    }
}
