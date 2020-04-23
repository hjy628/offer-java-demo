package chap9.command;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:17
 * @Description:
 */

public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe(String command) {
        receiver.action(command);
    }
}
