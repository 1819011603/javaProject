package 命令模式.控制中心;

import 命令模式.命令.具体实现类.Command;
import 命令模式.命令.具体实现类.NoCommand;

import java.util.Stack;

/**
 * @author 18190
 * @Date: 2023/1/9  22:27
 * @VERSION 1.0
 */
public class RemoteControl {
    private Command[] commands;

    private Stack<Command> historyCommands;

    public RemoteControl() {
        commands = new Command[7];

        for (int i = 0; i < 7; i++) {
            commands[i] = new NoCommand();

        }
        historyCommands = new Stack<>();
    }

    public void setCommand(int slot, Command onCommand) {
        commands[slot] = onCommand;

    }

    public void onButtonWasPressed(int slot) {
        commands[slot].execute();
        historyCommands.push(commands[slot]);
    }

    public void offButtonWasPressed(int slot) {
        commands[slot].execute();
        historyCommands.push(commands[slot]);
    }

    public void undo() {
        if (!historyCommands.isEmpty()){
            Command command = historyCommands.pop();
            if (null != command) {
                command.undo();
            }
        }else{
            System.out.println("无历史操作！");
        }
    }
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n------Remote Control------\n");
        for (int i = 0; i < commands.length; i++) {
            stringBuffer.append("[slot " + i + "]" + commands[i].getClass().getName() + "\n");
        }
        return stringBuffer.toString();
    }

}
