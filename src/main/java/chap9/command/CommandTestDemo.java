package chap9.command;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:20
 * @Description:
 */

public class CommandTestDemo {
    public static void main(String[] args) {

        //定义命令的接收和执行者
        Receiver receiver = new Receiver();
        //定义命令实现类
        Command command = new ConcreteCommand(receiver);
        //定义命令调用者
        Invoker invoker = new Invoker(command);
        //命令调用
        invoker.action("command1");

    }
}
