package 命令模式.命令.具体实现类;

import 命令模式.命令实际运行.具体被命令的东西.Light;

/**
 * @author 18190
 * @Date: 2023/1/9  22:18
 * @VERSION 1.0
 */
public class NoCommand implements Command{

    public NoCommand(){

    }
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
