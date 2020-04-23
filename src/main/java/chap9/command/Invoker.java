package chap9.command;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:19
 * @Description:
 */

public class Invoker {


    private final static Logger logger = Logger.getLogger("Invoker");

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(String commandMessage){
        logger.info("command sending.....");
        command.exe(commandMessage);
    }

}
