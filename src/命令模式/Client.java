package 命令模式;

import 命令模式.命令.具体实现类.LightCommand;
import 命令模式.命令.具体实现类.TvCommand;
import 命令模式.命令.具体实现类.WashMachineCommand;
import 命令模式.命令实际运行.Receiver;
import 命令模式.命令实际运行.具体被命令的东西.Light;
import 命令模式.命令实际运行.具体被命令的东西.Tv;
import 命令模式.命令实际运行.具体被命令的东西.WashMachine;
import 命令模式.控制中心.RemoteControl;

/**
 * @author 18190
 * @Date: 2023/1/9  22:12
 * @VERSION 1.0
 */


//  这东西可以看做为遥控器  最接触使用者的  ，但是功能不是他实现
public class Client {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();


        LightCommand lightCommand = new LightCommand(new Light());
        TvCommand tvCommand = new TvCommand(new Tv());
        WashMachineCommand washMachineCommand = new WashMachineCommand(new WashMachine());

        remoteControl.setCommand(0,lightCommand);
        remoteControl.setCommand(1,tvCommand);
        remoteControl.setCommand(2,washMachineCommand);




        remoteControl.onButtonWasPressed(0);//开
        remoteControl.undo();//关
        remoteControl.offButtonWasPressed(0);//关闭
        remoteControl.onButtonWasPressed(0);//开
        remoteControl.undo();//关
        remoteControl.undo();//开
        remoteControl.undo();//无操作


        remoteControl.onButtonWasPressed(1);//开
        remoteControl.undo();//关
        remoteControl.offButtonWasPressed(1);//关闭
        remoteControl.onButtonWasPressed(1);//开
        remoteControl.undo();//关
        remoteControl.undo();//开
        remoteControl.undo();//无操作

    }
}
