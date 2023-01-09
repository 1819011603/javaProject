package 命令模式.命令.具体实现类;

/**
 * @author 18190
 * @Date: 2023/1/9  22:18
 * @VERSION 1.0
 */
public interface Command {
    public void execute();
    public void undo();
}
